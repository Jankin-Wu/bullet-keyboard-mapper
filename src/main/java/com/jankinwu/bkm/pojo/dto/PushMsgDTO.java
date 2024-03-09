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

    /**
     * 由于 Native 打包方式不支持反射，只能手动序列化
     * @return JSON
     */
    public String toJsonString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("\"text\":").append(quoteString(text)).append(",");
        sb.append("\"fontSize\":").append(quoteString(fontSize)).append(",");
        sb.append("\"fill\":").append(quoteString(fill)).append(",");
        sb.append("\"stroke\":").append(quoteString(stroke)).append(",");
        sb.append("\"fontFamily\":").append(quoteString(fontFamily)).append(",");
        sb.append("\"type\":").append(quoteString(type)).append(",");
        sb.append("\"avatarUrl\":").append(quoteString(avatarUrl));
        sb.append("}");
        return sb.toString();
    }

    private String quoteString(String value) {
        if (value == null) {
            return "null";
        } else {
            return "\"" + value + "\"";
        }
    }
}
