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

    private BulletCommentRequest request;
    private ProcessData process;

    public RequestProcessContext(BulletCommentRequest request) {
        this.request = request;
    }
}
