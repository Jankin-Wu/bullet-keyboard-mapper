package com.jankinwu.bkm.handler;

/**
 * @author jankinwu
 * @description 弹幕处理责任链
 * @date 2024/3/3 2:20
 */
public abstract class AbstractBulletResponseHandlerChain implements BulletResponseHandlerChain {

    private BulletResponseHandlerChain next;

    private BulletResponseHandlerChain prev;

    @Override
    public BulletResponseHandlerChain getNext() {
        return next;
    }

    @Override
    public void setNext(BulletResponseHandlerChain next) {
        this.next = next;
        next.setPrev(this);
    }

    @Override
    public BulletResponseHandlerChain getPrev() {
        return prev;
    }

    @Override
    public void setPrev(BulletResponseHandlerChain prev) {
        this.prev = prev;
    }
}
