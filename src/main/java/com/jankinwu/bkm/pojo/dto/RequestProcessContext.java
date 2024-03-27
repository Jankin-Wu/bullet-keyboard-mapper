package com.jankinwu.bkm.pojo.dto;

import com.jankinwu.bkm.pojo.domain.ProcessData;
import com.jankinwu.bkm.pojo.request.BulletCommentRequest;
import lombok.Data;

/**
 * @author jankinwu
 * @description 消息上下文
 * @date 2024/3/3 19:48
 */
@Data
public class RequestProcessContext {

    private BulletCommentRequest bulletCommentRequest;
    private RequestCommonData commonData;
    private ProcessData process;
    private String type;

    public RequestProcessContext(BulletCommentRequest bulletCommentRequest) {
        this.bulletCommentRequest = bulletCommentRequest;
    }

    public RequestProcessContext(String type) {
        this.type = type;
    }
}
