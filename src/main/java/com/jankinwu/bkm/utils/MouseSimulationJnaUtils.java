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

    private static final int MOUSEEVENTF_WHEEL = 0x0800;
    private static final int WHEEL_DELTA = 120;
    private static final int SCROLL_AMOUNT_PER_NOTCH = 3;

    public static void clickEvent(int code) {
        User32.INSTANCE.mouse_event(code, 0, 0, 0, 0);
    }

    public static void release(String key) {
        Integer releaseCode = MouseEventJnaEnum.getReleaseCode(key);
        if (Objects.isNull(releaseCode)) {
            String errMsg = String.format("匹配不到按键：%s，请检查按键名称是否正确", key);
            throw new RuntimeException(errMsg);
        }
        clickEvent(releaseCode);
    }

    public static void press(String key) {
        Integer pressCode = MouseEventJnaEnum.getPressCode(key);
        if (Objects.isNull(pressCode)) {
            String errMsg = String.format("匹配不到按键：%s，请检查按键名称是否正确", key);
            throw new RuntimeException(errMsg);
        }
        clickEvent(pressCode);
    }

    public static void scroll(int amount) {
        // 判断滚动方向
        int direction = amount >= 0 ? -1 : 1;

        int wheelTimes;
        if (amount > 0) {
            wheelTimes = Math.abs((int) Math.ceil(amount / (double) SCROLL_AMOUNT_PER_NOTCH)); // 正数向上取整
        } else {
            wheelTimes = Math.abs((int) Math.floor(amount / (double) SCROLL_AMOUNT_PER_NOTCH)); // 负数向下取整
        }
        int wheelDelta = direction * WHEEL_DELTA; // 计算滚动距离
        for (int i = 0; i < wheelTimes; i++) {
            User32.INSTANCE.mouse_event(MOUSEEVENTF_WHEEL, 0, 0, wheelDelta, 0);
        }
    }

    public interface User32 extends Library {
        User32 INSTANCE = Native.load(Platform.isWindows() ? "user32" : "c", User32.class);

        void mouse_event(int dwFlags, int dx, int dy, int dwData, int dwExtraInfo);
    }
}
