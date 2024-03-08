package com.jankinwu.bkm.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author wwg
 * @description 组件枚举
 * @date 2024/3/8 1:09
 */
@Getter
@AllArgsConstructor
public enum PluginEnum {

    BULLET_COMMENT(1, "弹幕组件");

    private final int code;
    private final String name;

    public static String getName(int code) {
        for (PluginEnum pluginEnum : PluginEnum.values()) {
            if (pluginEnum.getCode() == code) {
                return pluginEnum.getName();
            }
        }
        return null;
    }
}
