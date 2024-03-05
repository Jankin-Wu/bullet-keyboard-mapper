package com.jankinwu.bkm.handler;

import com.jankinwu.bkm.pojo.dto.RequestProcessContext;

/**
 * @author jankinwu
 * @description 弹幕处理责任链
 * @date 2024/3/3 2:20
 */
public interface BulletResponseHandlerChain {

    /**
     * 处理请求
     * @param context 上下文参数
     */
    void doChain(RequestProcessContext context);

    /**
     * 获取下一个处理器
     * @return 下一个处理器
     */
    BulletResponseHandlerChain getNext();

    /**
     * 设置下一个处理器
     * @param next 下一个处理器
     */
    void setNext(BulletResponseHandlerChain next);

    /**
     * 获取前一个处理器
     * @return 前一个处理器
     */
    BulletResponseHandlerChain getPrev();

    /**
     * 设置前一个处理器
     * @param prev 前一个处理器
     */
    void setPrev(BulletResponseHandlerChain prev);
}
