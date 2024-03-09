package com.jankinwu.bkm.handler;

import com.jankinwu.bkm.config.AppConfig;
import com.jankinwu.bkm.enums.PluginEnum;
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
public class ExecutionInfoPushHandlerChain extends AbstractBulletResponseHandlerChain {

    private final PluginWebSocketSever pluginWebSocketSever;

    private final AppConfig appConfig;

    @Override
    public void doChain(RequestProcessContext context) {
        if (pluginWebSocketSever.isConnecting(PluginEnum.BULLET_COMMENT.getCode())) {
            Map<String, String> paramMap = assembleMap(context);
            String msg;
            if (StringUtils.isNotBlank(appConfig.getExecutionFormatString())) {
                msg = StringUtils.replacePlaceholders(appConfig.getExecutionFormatString(), paramMap);
            } else {
                msg = "[" + context.getRequest().getData().getUname() + "]" + "执行操作：" + context.getProcess().getProcessName();
            }
            pluginWebSocketSever.sendMessageByPlugin(PluginEnum.BULLET_COMMENT.getCode(), msg);
        }
        if (getNext() != null) {
            getNext().doChain(context);
        }
    }

    private Map<String, String> assembleMap(RequestProcessContext context) {
        Map<String, String> map = new HashMap<>();
        map.put("uname", context.getRequest().getData().getUname());
        map.put("processName", context.getProcess().getProcessName());
        return map;
    }
}
