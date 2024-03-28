package com.jankinwu.bkm.pojo.receive;

import lombok.Data;

/**
 * @author jankinwu
 * @description 接收直播弹幕推送消息体中data实体类
 * @date 2024/3/3 21:33
 */
@Data
public class BulletCommentReceiveData {

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
    private String openId;
    private String msgId;
    private int roomId;
}
