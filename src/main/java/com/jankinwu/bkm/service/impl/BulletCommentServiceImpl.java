package com.jankinwu.bkm.service.impl;

import com.jankinwu.bkm.enums.LiveMsgTypeEnum;
import com.jankinwu.bkm.handler.*;
import com.jankinwu.bkm.pojo.dto.RequestCommonData;
import com.jankinwu.bkm.pojo.dto.RequestProcessContext;
import com.jankinwu.bkm.pojo.request.BulletCommentRequest;
import com.jankinwu.bkm.service.LiveMsgService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * @author jankinwu
 * @description 弹幕处理
 * @date 2024/3/3 15:27
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class BulletCommentServiceImpl implements LiveMsgService {
    
    private final ProcessMappingHandlerChain processMappingHandlerChain;

    private final ProcessHandleChain processHandleChain;

    private final DelayTaskHandlerChain delayTaskHandlerChain;

    private final LimitRateHandlerChain limitRateHandlerChain;

    private final PushMsgHandlerChain pushMsgHandlerChain;

    @Override
    public void handle(String content, RequestProcessContext context) {
        BulletCommentRequest request = BulletCommentRequest.parseRequest(content);
        context.setBulletCommentRequest(request);
        RequestCommonData commonData = new RequestCommonData();
        BeanUtils.copyProperties(request.getData(), commonData);
        context.setCommonData(commonData);
        String msg = context.getBulletCommentRequest().getData().getMsg();
        log.info("[弹幕] {}: {}", context.getBulletCommentRequest().getData().getUname(), msg);
        limitRateHandlerChain.setNext(processMappingHandlerChain);
        processMappingHandlerChain.setNext(delayTaskHandlerChain);
        delayTaskHandlerChain.setNext(processHandleChain);
        processHandleChain.setNext(pushMsgHandlerChain);
        limitRateHandlerChain.doChain(context);
    }

    @Override
    public String getMsgType() {
        return LiveMsgTypeEnum.BULLET_COMMENT.getType();
    }
}
