package com.jankinwu.bkm.pojo.dto;

import lombok.Data;

/**
 * @author jankinwu
 * @description
 * @date 2024/3/9 2:59
 */
@Data
public class PushMsgDTO {

    private String text;
    private String fontSize;
    private String fill;
    private String stroke;
    private String fontFamily;
    private String type;
    private String avatarUrl;
}
