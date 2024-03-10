package com.jankinwu.bkm.service;

import com.jankinwu.bkm.handler.*;
import com.jankinwu.bkm.pojo.dto.RequestProcessContext;
import com.jankinwu.bkm.pojo.request.BulletCommentRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    
    private final ProcessMappingHandlerChain processMappingHandlerChain;

    private final ProcessHandleChain processHandleChain;

    private final DelayTaskHandlerChain delayTaskHandlerChain;

    private final LimitRateHandlerChain limitRateHandlerChain;

    private final PushMsgHandlerChain pushMsgHandlerChain;

    public void handle(String content) {
        BulletCommentRequest request = BulletCommentRequest.parseRequest(content);
        RequestProcessContext context = new RequestProcessContext(request);
        limitRateHandlerChain.setNext(processMappingHandlerChain);
        processMappingHandlerChain.setNext(delayTaskHandlerChain);
        delayTaskHandlerChain.setNext(processHandleChain);
        processHandleChain.setNext(pushMsgHandlerChain);
        limitRateHandlerChain.doChain(context);
    }
}
