package com.jankinwu.bkm.pojo.request;

import lombok.Data;

/**
 * @author jankinwu
 * @description
 * @date 2024/3/3 21:33
 */
@Data
public class BulletCommentRequestData {

    private String emojiImgUrl;
    private int fansMedalLevel;
    private String fansMedalName;
    private boolean fansMedalWearingStatus;
    private int guardLevel;
    private String msg;
    private long timestamp;
    private int uid;
    private String uname;
    private String uface;
    private int dmType;
    private String msgId;
    private int roomId;
}
