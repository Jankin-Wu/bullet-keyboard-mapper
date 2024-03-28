package com.jankinwu.bkm.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jankinwu.bkm.enums.LiveMsgTypeEnum;
import com.jankinwu.bkm.handler.*;
import com.jankinwu.bkm.pojo.dto.RequestCommonData;
import com.jankinwu.bkm.pojo.dto.RequestProcessContext;
import com.jankinwu.bkm.pojo.receive.GiftReceive;
import com.jankinwu.bkm.service.LiveMsgService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * @author jankinwu
 * @description 礼物消息处理
 * @date 2024/3/27 0:03
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class GiftServiceImpl implements LiveMsgService {

    private final ProcessMappingHandlerChain processMappingHandlerChain;

    private final ProcessHandleChain processHandleChain;

    private final DelayTaskHandlerChain delayTaskHandlerChain;

    private final PushMsgHandlerChain pushMsgHandlerChain;

    @Override
    public void handle(String content, RequestProcessContext context) {
        ObjectMapper objectMapper = new ObjectMapper();
        GiftReceive giftReceive;
        try {
            giftReceive = objectMapper.readValue(content, GiftReceive.class);
        } catch (JsonProcessingException e) {
            log.error("Json 解析异常：", e);
            return;
        }
        RequestCommonData commonData = new RequestCommonData();
        BeanUtils.copyProperties(giftReceive, commonData);
        commonData.setMsg(giftReceive.getData().getGiftName());
        context.setCommonData(commonData);
        String msg = context.getBulletCommentReceive().getData().getMsg();
        log.info("[礼物] {}: {}", context.getBulletCommentReceive().getData().getUname(), msg);
        processMappingHandlerChain.setNext(delayTaskHandlerChain);
        delayTaskHandlerChain.setNext(processHandleChain);
        processHandleChain.setNext(pushMsgHandlerChain);
        processMappingHandlerChain.doChain(context);
    }

    @Override
    public String getMsgType() {
        return LiveMsgTypeEnum.GIFT.getType();
    }
}
