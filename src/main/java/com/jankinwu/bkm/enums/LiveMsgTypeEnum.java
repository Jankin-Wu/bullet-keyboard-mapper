package com.jankinwu.bkm.enums;

import com.jankinwu.bkm.constants.Constants;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

/**
 * @author jankinwu
 * @description 直播消息类型枚举
 * @date 2024/3/26 18:57
 */
@Getter
@AllArgsConstructor
public enum LiveMsgTypeEnum {

    BULLET_COMMENT("BULLET_COMMENT", "弹幕", Constants.BULLET_COMMENT_CMD_LIST),
    GIFT("GIFT", "礼物", Constants.GIFT_CMD_LIST),
    LIKE("LIKE", "点赞", Constants.LIKE_CMD_LIST),
    ;

    private final String type;
    private final String name;
    private final List<String> cmdList;

    public static String getTypeByCmd(String cmd) {
        return Arrays.stream(values())
                .filter(type -> type.getCmdList().contains(cmd))
                .findFirst()
                .map(LiveMsgTypeEnum::getType)
                .orElse(null);
    }
}
