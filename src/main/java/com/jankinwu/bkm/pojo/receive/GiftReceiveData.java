package com.jankinwu.bkm.pojo.receive;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jankinwu
 * @description 接收直播礼物消息推送消息体中 data 实体类
 * @date 2024/3/26 16:29
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(builderClassName = "GiftRequestDataBuilder", builderMethodName = "giftRequestDataBuilder")
@JsonDeserialize(builder = GiftReceiveData.GiftRequestDataBuilder.class)
@JsonSerialize
public class GiftReceiveData {
    private int uid;
    private String uname;
    private String uface;

    @JsonProperty("gift_id")
    private int giftId;

    @JsonProperty("gift_name")
    private String giftName;

    @JsonProperty("gift_num")
    private int giftNum;
    private int price;
    private boolean paid;

    @JsonProperty("fans_medal_level")
    private int fansMedalLevel;

    @JsonProperty("fans_medal_name")
    private String fansMedalName;

    @JsonProperty("fans_medal_wearing_status")
    private boolean fansMedalWearingStatus;

    @JsonProperty("guard_level")
    private int guardLevel;

    private int timestamp;

    @JsonProperty("anchor_info")
    private AnchorInfo anchorInfo;

    @JsonProperty("gift_icon")
    private String giftIcon;

    @JsonProperty("combo_gift")
    private boolean comboGift;

    @JsonProperty("combo_info")
    private ComboInfo comboInfo;

    @JsonProperty("open_id")
    private String openId;

    @JsonProperty("msg_id")
    private String msgId;

    @JsonProperty("room_id")
    private int roomId;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder(builderClassName = "AnchorInfoBuilder", builderMethodName = "anchorInfoBuilder")
    @JsonDeserialize(builder = GiftReceiveData.AnchorInfo.AnchorInfoBuilder.class)
    public static class AnchorInfo {
        private String uface;
        private int uid;
        private String uname;
        @JsonProperty("open_id")
        private String openId;

        @JsonPOJOBuilder(withPrefix = "")
        public static class AnchorInfoBuilder {

        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder(builderClassName = "ComboInfoBuilder", builderMethodName = "comboInfoBuilder")
    @JsonDeserialize(builder = GiftReceiveData.ComboInfo.ComboInfoBuilder.class)
    public static class ComboInfo {

        @JsonProperty("combo_base_num")
        private int comboBaseNum;

        @JsonProperty("combo_count")
        private int comboCount;

        @JsonProperty("combo_id")
        private String comboId;

        @JsonProperty("combo_timeout")
        private int comboTimeout;

        @JsonPOJOBuilder(withPrefix = "")
        public static class ComboInfoBuilder {

        }
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static class GiftRequestDataBuilder {

    }
}
