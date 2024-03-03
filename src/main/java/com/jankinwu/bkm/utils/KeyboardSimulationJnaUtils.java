package com.jankinwu.bkm.utils;


import com.jankinwu.bkm.enums.KeyMappingJnaEnum;
import com.jankinwu.bkm.hints.KeyboardSimuRuntimeHints;
import com.sun.jna.Library;
import com.sun.jna.Native;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ImportRuntimeHints;

import java.util.Objects;


/**
 * @author jankinwu
 * @description
 * @date 2024/2/25 16:46
 */
@Slf4j
@ImportRuntimeHints(KeyboardSimuRuntimeHints.class)
public class KeyboardSimulationJnaUtils {

    public static void pressAndRelease(String keyName) {
        Byte keyCode = KeyMappingJnaEnum.getKeyCode(keyName);
        if (Objects.nonNull(keyCode)) {
            pressAndRelease(keyCode);
            log.info("模拟按键：{}", keyName);
        }
    }

    public static void pressAndRelease(byte keyCode) {
        User32 user32 = User32.INSTANCE;

        user32.keybd_event(keyCode, (byte) 0, 0, 0);
        user32.keybd_event(keyCode, (byte) 0, 2, 0);
    }

    public interface User32 extends Library {
        User32 INSTANCE = Native.load("user32", User32.class);

        void keybd_event(byte bVk, byte bScan, int dwFlags, int dwExtraInfo);
    }

    public static void press(String keyName) {
        Byte keyCode = KeyMappingJnaEnum.getKeyCode(keyName);
        if (Objects.nonNull(keyCode)) {
            press(keyCode);
        }
    }

    public static void release(String keyName) {
        Byte keyCode = KeyMappingJnaEnum.getKeyCode(keyName);
        if (Objects.nonNull(keyCode)) {
            release(keyCode);
        }
    }

    public static void press(byte keyCode) {
        User32 user32 = User32.INSTANCE;
        user32.keybd_event(keyCode, (byte) 0, 0, 0);
    }

    public static void release(byte keyCode) {
        User32 user32 = User32.INSTANCE;
        user32.keybd_event(keyCode, (byte) 0, 2, 0);
    }
}
