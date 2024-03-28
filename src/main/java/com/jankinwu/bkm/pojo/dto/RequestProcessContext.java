package com.jankinwu.bkm.pojo.dto;

import com.jankinwu.bkm.pojo.domain.ProcessData;
import com.jankinwu.bkm.pojo.receive.BulletCommentReceive;
import lombok.Data;

/**
 * @author jankinwu
 * @description 消息上下文
 * @date 2024/3/3 19:48
 */
@Data
public class RequestProcessContext {

    private BulletCommentReceive bulletCommentReceive;
    private RequestCommonData commonData;
    private ProcessData process;
    private String type;

    public RequestProcessContext(BulletCommentReceive bulletCommentReceive) {
        this.bulletCommentReceive = bulletCommentReceive;
    }

    public RequestProcessContext(String type) {
        this.type = type;
    }
}
