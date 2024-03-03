package com.jankinwu.bkm.handler;

import com.jankinwu.bkm.pojo.request.BulletCommentRequest;

/**
 * @author jankinwu
 * @description 弹幕处理责任链
 * @date 2024/3/3 2:20
 */
public abstract class AbstractBulletCommentHandlerChain implements BulletCommentHandlerChain{

    private BulletCommentHandlerChain next;

    private BulletCommentHandlerChain prev;

    @Override
    public BulletCommentHandlerChain getNext() {
        return next;
    }

    @Override
    public void setNext(BulletCommentHandlerChain next) {
        this.next = next;
        next.setPrev(this);
    }

    @Override
    public BulletCommentHandlerChain getPrev() {
        return prev;
    }

    @Override
    public void setPrev(BulletCommentHandlerChain prev) {
        this.prev = prev;
    }
}
