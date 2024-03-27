package com.jankinwu.bkm.service.impl;

import com.jankinwu.bkm.enums.LiveMsgTypeEnum;
import com.jankinwu.bkm.pojo.dto.RequestProcessContext;
import com.jankinwu.bkm.service.LiveMsgService;
import org.springframework.stereotype.Service;

/**
 * @author jankinwu
 * @description 点赞消息处理
 * @date 2024/3/27 0:06
 */
@Service
public class LikeServiceImpl implements LiveMsgService {
    @Override
    public void handle(String content, RequestProcessContext context) {

    }

    @Override
    public String getMsgType() {
        return LiveMsgTypeEnum.LIKE.getType();
    }
}
