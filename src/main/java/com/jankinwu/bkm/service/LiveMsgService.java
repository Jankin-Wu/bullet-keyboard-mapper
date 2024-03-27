package com.jankinwu.bkm.service;

import com.jankinwu.bkm.pojo.dto.RequestProcessContext;

/**
 * @author jankinwu
 * @description 直播消息处理
 * @date 2024/3/26 18:46
 */
public interface LiveMsgService {

    /**
     * 直播消息处理
     *
     * @param content 消息内容
     * @param context 处理链上下文
     */
    void handle(String content, RequestProcessContext context);

    /**
     * 获取消息类型
     * @return type 消息类型
     */
    String getMsgType();
}
