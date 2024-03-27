package com.jankinwu.bkm.pojo.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jankinwu
 * @description
 * @date 2024/3/26 16:27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderClassName = "GiftRequestBuilder", builderMethodName = "giftRequestBuilder")
@JsonDeserialize(builder = GiftRequest.GiftRequestBuilder.class)
public class GiftRequest {

    @JsonProperty("data")
    private GiftRequestData data;

    @JsonProperty("cmd")
    private String cmd;

    @JsonPOJOBuilder(withPrefix = "")
    public static class GiftRequestBuilder {

    }
//
//    public static GiftRequest parseJson(String jsonString) {
//        JSONObject jsonObject = JSON.parseObject(jsonString);
//
//        GiftRequest request = new GiftRequest();
//        request.setCmd(jsonObject.getString("cmd"));
//
//        JSONObject dataObject = jsonObject.getJSONObject("data");
//        GiftRequestData data = new GiftRequestData();
//        data.setUid(dataObject.getIntValue("uid"));
//        data.setUname(dataObject.getString("uname"));
//        data.setUface(dataObject.getString("uface"));
//        data.setGiftId(dataObject.getIntValue("gift_id"));
//        data.setGiftName(dataObject.getString("gift_name"));
//        data.setGiftNum(dataObject.getIntValue("gift_num"));
//        data.setPrice(dataObject.getIntValue("price"));
//        data.setPaid(dataObject.getBooleanValue("paid"));
//        data.setFansMedalLevel(dataObject.getIntValue("fans_medal_level"));
//        data.setFansMedalName(dataObject.getString("fans_medal_name"));
//        data.setFansMedalWearingStatus(dataObject.getBooleanValue("fans_medal_wearing_status"));
//        data.setGuardLevel(dataObject.getIntValue("guard_level"));
//        data.setTimestamp(dataObject.getIntValue("timestamp"));
//
//        JSONObject anchorInfoObject = dataObject.getJSONObject("anchor_info");
//        GiftRequestData.AnchorInfo anchorInfo = new GiftRequestData.AnchorInfo();
//        anchorInfo.setUface(anchorInfoObject.getString("uface"));
//        anchorInfo.setUid(anchorInfoObject.getIntValue("uid"));
//        anchorInfo.setUname(anchorInfoObject.getString("uname"));
//        anchorInfo.setOpenId(anchorInfoObject.getString("open_id"));
//        data.setAnchorInfo(anchorInfo);
//
//        data.setGiftIcon(dataObject.getString("gift_icon"));
//        data.setComboGift(dataObject.getBooleanValue("combo_gift"));
//
//        JSONObject comboInfoObject = dataObject.getJSONObject("combo_info");
//        GiftRequestData.ComboInfo comboInfo = new GiftRequestData.ComboInfo();
//        comboInfo.setComboBaseNum(comboInfoObject.getIntValue("combo_base_num"));
//        comboInfo.setComboCount(comboInfoObject.getIntValue("combo_count"));
//        comboInfo.setComboId(comboInfoObject.getString("combo_id"));
//        comboInfo.setComboTimeout(comboInfoObject.getIntValue("combo_timeout"));
//        data.setComboInfo(comboInfo);
//
//        data.setOpenId(dataObject.getString("open_id"));
//        data.setMsgId(dataObject.getString("msg_id"));
//        data.setRoomId(dataObject.getIntValue("room_id"));
//
//        request.setData(data);
//        return request;
//    }
}
