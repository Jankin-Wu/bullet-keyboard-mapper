package com.jankinwu.bkm.service;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.jankinwu.bkm.config.BasicConfig;
import com.jankinwu.bkm.handler.BulletCommentHandler;
import com.jankinwu.bkm.handler.DelayTaskHandlerChain;
import com.jankinwu.bkm.handler.ProcessHandleChain;
import com.jankinwu.bkm.pojo.dto.RequestProcessContext;
import com.jankinwu.bkm.pojo.request.BulletCommentRequest;
import com.jankinwu.bkm.pojo.request.BulletCommentRequestData;
import com.jankinwu.bkm.queue.DelayedTask;
import com.jankinwu.bkm.queue.DelayedTaskManager;
import com.jankinwu.bkm.queue.ScheduledQueueExecutor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author jankinwu
 * @description 弹幕处理
 * @date 2024/3/3 15:27
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class BulletCommentService {
    
    private final BulletCommentHandler bulletCommentHandler;

    private final ProcessHandleChain processHandleChain;

    private final DelayedTaskManager delayedTaskManager;

    private final DelayTaskHandlerChain delayTaskHandlerChain;

    private final BasicConfig basicConfig;

    public void handle(String content) {
        BulletCommentRequest request = parseRequest(content);
        RequestProcessContext context = new RequestProcessContext(request);
        bulletCommentHandler.setNext(delayTaskHandlerChain);
        delayTaskHandlerChain.setNext(processHandleChain);
        bulletCommentHandler.doChain(context);

    }



    /**
     * 由于使用native方式打包时候不支持反射，这里采用手动解析的方式
     * @param content 弹幕消息
     * @return BulletCommentRequest
     */
    public BulletCommentRequest parseRequest(String content) {
        JSONObject jsonObject = JSON.parseObject(content);
        BulletCommentRequest request = new BulletCommentRequest();
        JSONObject dataObject = jsonObject.getJSONObject("data");
        BulletCommentRequestData data = new BulletCommentRequestData();
        data.setEmojiImgUrl(dataObject.getString("emojiImgUrl"));
        data.setFansMedalLevel(dataObject.getIntValue("fansMedalLevel"));
        data.setFansMedalName(dataObject.getString("fansMedalName"));
        data.setFansMedalWearingStatus(dataObject.getBooleanValue("fansMedalWearingStatus"));
        data.setGuardLevel(dataObject.getIntValue("guardLevel"));
        data.setMsg(dataObject.getString("msg"));
        data.setTimestamp(dataObject.getLongValue("timestamp"));
        data.setUid(dataObject.getIntValue("uid"));
        data.setUname(dataObject.getString("uname"));
        data.setUface(dataObject.getString("uface"));
        data.setDmType(dataObject.getIntValue("dmType"));
        data.setMsgId(dataObject.getString("msgId"));
        data.setRoomId(dataObject.getIntValue("roomId"));
        request.setData(data);
        request.setCmd(jsonObject.getString("cmd"));
        return request;
    }
}
