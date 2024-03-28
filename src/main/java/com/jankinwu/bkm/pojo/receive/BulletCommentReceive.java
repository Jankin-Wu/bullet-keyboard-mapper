package com.jankinwu.bkm.pojo.receive;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import lombok.Data;

/**
 * @author jankinwu
 * @description 接收直播弹幕推送实体类
 * @date 2024/3/3 2:40
 */
@Data
public class BulletCommentReceive {

    private BulletCommentReceiveData data;
    private String cmd;

    /**
     * 由于使用native方式打包时候不支持反射，这里采用手动解析的方式
     * @param content 弹幕消息
     * @return BulletCommentRequest
     */
    public static BulletCommentReceive parseRequest(String content) {
        JSONObject jsonObject = JSON.parseObject(content);
        BulletCommentReceive request = new BulletCommentReceive();
        JSONObject dataObject = jsonObject.getJSONObject("data");
        BulletCommentReceiveData data = new BulletCommentReceiveData();
        data.setEmojiImgUrl(dataObject.getString("emoji_img_url"));
        data.setFansMedalLevel(dataObject.getIntValue("fans_medal_level"));
        data.setFansMedalName(dataObject.getString("fans_medal_name"));
        data.setFansMedalWearingStatus(dataObject.getBooleanValue("fans_medal_wearing_status"));
        data.setGuardLevel(dataObject.getIntValue("guard_level"));
        data.setMsg(dataObject.getString("msg"));
        data.setTimestamp(dataObject.getLongValue("timestamp"));
        data.setUid(dataObject.getIntValue("uid"));
        data.setUname(dataObject.getString("uname"));
        data.setUface(dataObject.getString("uface"));
        data.setDmType(dataObject.getIntValue("dm_type"));
        data.setOpenId(dataObject.getString("open_id"));
        data.setMsgId(dataObject.getString("msg_id"));
        data.setRoomId(dataObject.getIntValue("room_id"));
        request.setData(data);
        request.setCmd(jsonObject.getString("cmd"));
        return request;
    }
}
