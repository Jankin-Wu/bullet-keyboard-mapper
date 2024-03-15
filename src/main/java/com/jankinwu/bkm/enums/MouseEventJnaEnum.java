package com.jankinwu.bkm.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author jankinwu
 * @description 鼠标事件枚举
 * @date 2024/3/13 21:42
 */
@Getter
@AllArgsConstructor
public enum MouseEventJnaEnum {

    LEFT("LEFT", 0x0002, 0x0004),
    MIDDLE("MIDDLE", 0x0020, 0x0040),
    RIGHT("RIGHT", 0x0008, 0x0010);

    private final String name;
    private final int pressCode;
    private final int releaseCode;

    public static Integer getPressCode(String name) {
        for (MouseEventJnaEnum mouseEventJnaEnum : MouseEventJnaEnum.values()) {
            if (mouseEventJnaEnum.getName().equals(name)) {
                return mouseEventJnaEnum.getPressCode();
            }
        }
        return null;
    }

    public static Integer getReleaseCode(String name) {
        for (MouseEventJnaEnum mouseEventJnaEnum : MouseEventJnaEnum.values()) {
            if (mouseEventJnaEnum.getName().equals(name)) {
                return mouseEventJnaEnum.getReleaseCode();
            }
        }
        return null;
    }
}
