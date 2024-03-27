package com.jankinwu.bkm.pojo.dto;

import lombok.Data;

/**
 * @author jankinwu
 * @description 消息通用数据
 * @date 2024/3/27 10:53
 */
@Data
public class RequestCommonData {

    private int guardLevel;
    private int fansMedalLevel;
    private String fansMedalName;
    private String msg;
    private long timestamp;
    private int uid;
    private String uname;
    private String uface;
    private String openId;
    private String msgId;
    private int roomId;
    private String cmd;
}
