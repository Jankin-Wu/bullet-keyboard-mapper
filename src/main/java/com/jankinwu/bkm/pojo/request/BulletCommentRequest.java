package com.jankinwu.bkm.pojo.request;

import lombok.Data;

/**
 * @author jankinwu
 * @description 弹幕请求体
 * @date 2024/3/3 2:40
 */
@Data
public class BulletCommentRequest {

    private BulletCommentRequestData data;
    private String cmd;
}
