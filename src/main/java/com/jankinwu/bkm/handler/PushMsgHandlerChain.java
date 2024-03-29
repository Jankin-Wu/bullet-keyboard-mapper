package com.jankinwu.bkm.handler;

import cn.hutool.core.collection.CollUtil;
import com.jankinwu.bkm.config.AppConfig;
import com.jankinwu.bkm.config.PushMsgConfig;
import com.jankinwu.bkm.enums.PluginEnum;
import com.jankinwu.bkm.pojo.dto.PushMsgDTO;
import com.jankinwu.bkm.pojo.dto.RequestProcessContext;
import com.jankinwu.bkm.utils.StringUtils;
import com.jankinwu.bkm.ws.PluginWebSocketSever;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jankinwu
 * @description 任务执行信息推送处理
 * @date 2024/3/7 18:21
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class PushMsgHandlerChain extends AbstractBulletResponseHandlerChain {

    private final PluginWebSocketSever pluginWebSocketSever;

    private final AppConfig appConfig;

    private final PushMsgConfig pushMsgConfig;

    @Override
    public void doChain(RequestProcessContext context) {
        // 过滤不想推送给插件的 process
        if (CollUtil.isNotEmpty(appConfig.getProcessesDontPush()) && appConfig.getProcessesDontPush().contains(context.getProcess().getProcessName())) {
            return;
        }
        if (pluginWebSocketSever.isConnecting(PluginEnum.BULLET_COMMENT.getCode())) {
            PushMsgDTO dto = assemblePushMsgDTO(context);
            pluginWebSocketSever.sendMessageByPlugin(PluginEnum.BULLET_COMMENT.getCode(), dto);
        }
        if (getNext() != null) {
            getNext().doChain(context);
        }
    }

    private PushMsgDTO assemblePushMsgDTO(RequestProcessContext context) {
        Map<String, String> paramMap = assembleMap(context);
        String msg;
        if (StringUtils.isNotBlank(appConfig.getExecutionFormatString())) {
            msg = StringUtils.replacePlaceholders(appConfig.getExecutionFormatString(), paramMap);
        } else {
            msg = "[" + context.getBulletCommentReceive().getData().getUname() + "]" + "执行操作：" + context.getProcess().getProcessName();
        }
        PushMsgDTO dto = new PushMsgDTO();
        dto.setFill(pushMsgConfig.getFill());
        dto.setFontFamily(pushMsgConfig.getFontFamily());
        dto.setStroke(pushMsgConfig.getStroke());
        dto.setFontSize(pushMsgConfig.getFontSize());
        dto.setText(msg);
        dto.setType(pushMsgConfig.getType());
        dto.setAvatarUrl(context.getBulletCommentReceive().getData().getUface());
        return dto;
    }

    private Map<String, String> assembleMap(RequestProcessContext context) {
        Map<String, String> map = new HashMap<>();
        map.put("uname", context.getBulletCommentReceive().getData().getUname());
        map.put("processName", context.getProcess().getProcessName());
        return map;
    }
}
