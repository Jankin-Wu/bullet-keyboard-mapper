package com.jankinwu.bkm.enums;

import com.jankinwu.bkm.utils.KeyboardSimulationAwtUtils;
import com.jankinwu.bkm.utils.KeyboardSimulationJnaUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author jankinwu
 * @description 键盘模拟方案
 * @date 2024/3/2 16:31
 */
@AllArgsConstructor
@Getter
public enum KeySimMethodEnum {
    AWT("awt", KeyboardSimulationAwtUtils.class),
    JNA("jna", KeyboardSimulationJnaUtils.class);

    private final String name;
    private final Class<?> clazz;

    public static Class<?> getSimUtils(String name) {
        for (KeySimMethodEnum keySimMethodEnum : KeySimMethodEnum.values()) {
            if (keySimMethodEnum.getName().equals(name)) {
                return keySimMethodEnum.getClazz();
            }
        }
        return null;
    }
}
