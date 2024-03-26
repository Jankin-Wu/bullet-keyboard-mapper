package com.jankinwu.bkm.pojo.request;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
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

    /**
     * 由于使用native方式打包时候不支持反射，这里采用手动解析的方式
     * @param content 弹幕消息
     * @return BulletCommentRequest
     */
    public static BulletCommentRequest parseRequest(String content) {
        JSONObject jsonObject = JSON.parseObject(content);
        BulletCommentRequest request = new BulletCommentRequest();
        JSONObject dataObject = jsonObject.getJSONObject("data");
        BulletCommentRequestData data = new BulletCommentRequestData();
        data.setEmojiImgUrl(dataObject.getString("emojiImgUrl"));
        data.setFansMedalLevel(dataObject.getIntValue("fansMedalLevel"));
        data.setFansMedalName(dataObject.getString("fansMedalName"));
        data.setFansMedalWearingStatus(dataObject.getBooleanValue("fansMedalWearingStatus"));
        data.setGuardLevel(dataObject.getIntValue("guardLevel"));
        data.setMsg(dataObject.getString("msg"));
        data.setTimestamp(dataObject.getLongValue("timestamp"));
        data.setUid(dataObject.getIntValue("uid"));
        data.setUname(dataObject.getString("uname"));
        data.setUface(dataObject.getString("uface"));
        data.setDmType(dataObject.getIntValue("dmType"));
        data.setOpenId(dataObject.getString("open_id"));
        data.setMsgId(dataObject.getString("msgId"));
        data.setRoomId(dataObject.getIntValue("roomId"));
        request.setData(data);
        request.setCmd(jsonObject.getString("cmd"));
        return request;
    }
}
