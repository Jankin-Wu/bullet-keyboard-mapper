package com.jankinwu.bkm.utils;

import com.jankinwu.bkm.enums.MouseEventJnaEnum;
import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Platform;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

/**
 * @author jankinwu
 * @description 基于Jna实现的鼠标模拟工具类
 * @date 2024/3/13 21:43
 */
@Slf4j
public class MouseSimulationJnaUtils {

    public static void clickEvent(int code) {
        User32.INSTANCE.mouse_event(code, 0, 0, 0, 0);
    }

    public static void release(String key) {
        Integer releaseCode = MouseEventJnaEnum.getReleaseCode(key);
        if (Objects.isNull(releaseCode)) {
            log.warn("找不到对应的按键：{}，请检查按键名称是否正确", key);
            return;
        }
        clickEvent(releaseCode);
    }

    public static void press(String key) {
        Integer pressCode = MouseEventJnaEnum.getPressCode(key);
        if (Objects.isNull(pressCode)) {
            log.warn("找不到对应的按键：{}，请检查按键名称是否正确", key);
            return;
        }
        clickEvent(pressCode);
    }

    public static void scroll(int amount) {
        User32.INSTANCE.mouse_event(MouseEventJnaEnum.WHEEL.getPressCode(), 0, 0, amount, 0);
    }

    public interface User32 extends Library {
        User32 INSTANCE = Native.load(Platform.isWindows() ? "user32" : "c", User32.class);

        void mouse_event(int dwFlags, int dx, int dy, int dwData, int dwExtraInfo);
    }
}
