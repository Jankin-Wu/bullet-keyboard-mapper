package com.jankinwu.bkm.handler;

import com.jankinwu.bkm.enums.PluginEnum;
import com.jankinwu.bkm.pojo.dto.RequestProcessContext;
import com.jankinwu.bkm.ws.PluginWebSocketSever;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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

    @Override
    public void doChain(RequestProcessContext context) {
        String msg = "[" + context.getRequest().getData().getUname() + "]" + "执行操作：" + context.getProcess().getProcessName();
        pluginWebSocketSever.sendMessageByPlugin(PluginEnum.BULLET_COMMENT.getCode(), msg);
        if (getNext() != null) {
            getNext().doChain(context);
        }
    }
}
