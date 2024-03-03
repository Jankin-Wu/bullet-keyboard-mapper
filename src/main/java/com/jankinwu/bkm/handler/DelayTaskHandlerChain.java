package com.jankinwu.bkm.handler;

import com.jankinwu.bkm.pojo.dto.RequestProcessContext;

/**
 * @author wwg
 * @description 延时队列处理
 * @date 2024/3/3 22:33
 */
public class DelayTaskHandlerChain extends AbstractBulletCommentHandlerChain {

    @Override
    public void doChain(RequestProcessContext context) {
        if (getNext() != null) {
            getNext().doChain(context);
        }
    }
}
