package com.jankinwu.bkm.utils;


import com.jankinwu.bkm.enums.KeyMappingAwtEnum;
import com.jankinwu.bkm.hints.KeyboardSimuRuntimeHints;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ImportRuntimeHints;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Objects;


/**
 * @author jankinwu
 * @description
 * @date 2024/2/25 16:46
 */
@Slf4j
@ImportRuntimeHints(KeyboardSimuRuntimeHints.class)
public class KeyboardSimulationAwtUtils {

    public static void pressAndRelease(char character) {
        // 转换为大写字符以便处理
        character = Character.toUpperCase(character);

        // 检查输入是否为有效的字母（A-Z）或数字（0-9）
        if ((character < 'A' || character > 'Z') && (character < '0' || character > '9')) {
            log.info("传入的参数不是有效的字母或数字！");
            return;
        }

        int keyCode;

        if (Character.isLetter(character)) {
            // 计算字母按键的ASCII码值（'A'对应65，以此类推）
            keyCode = character - 'A' + KeyEvent.VK_A;
        } else {
            // 计算数字按键的ASCII码值（'0'对应48，以此类推）
            keyCode = character - '0' + KeyEvent.VK_0;
        }

        try {
            // 创建一个Robot对象实例
            Robot robot = new Robot();

            // 模拟按下按键
            robot.keyPress(keyCode);

            // 释放按键
            robot.keyRelease(keyCode);
        } catch (AWTException e) {
            log.error("无法创建或使用Robot对象：" + e.getMessage());
        }
    }

    public static void pressAndRelease(int eventCode) {
        try {
            // 创建一个Robot对象实例
            Robot robot = new Robot();
            log.info("Robot 对象创建成功");
            // 模拟按下按键
            robot.keyPress(eventCode);

            // 释放按键
            robot.keyRelease(eventCode);
        } catch (AWTException e) {
            e.printStackTrace();
            log.error("无法创建或使用Robot对象：{}", e.getMessage());
        }
    }

    public static void pressAndRelease(String keyName) {
        Integer eventCode = KeyMappingAwtEnum.getEventCode(keyName);
        if (Objects.nonNull(eventCode)) {
            pressAndRelease(eventCode);
            log.info("模拟按键：{}", keyName);
        }
    }
}
