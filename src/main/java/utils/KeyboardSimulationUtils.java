package utils;


import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * @author jankinwu
 * @description
 * @date 2024/2/25 16:46
 */
public class KeyboardSimulationUtils {

//    public static void pressAndRelease(char character) {
//        // 转换为大写字符以便处理
//        character = Character.toUpperCase(character);
//        // 由于 GraalVM 不支持 awt，改为使用jna实现模拟按键操作
//        short vkCode = getVkCode(character);
//
//        // 按下按键
//        WinUser.INPUT input = pressKey(vkCode);
//
//        User32.INSTANCE.SendInput(new WinDef.DWORD(1), (WinUser.INPUT[]) input.toArray(1), input.size());
//
//        // 释放按键
//        releaseKey(input);
//
//    }
//
//    private static void releaseKey(WinUser.INPUT input) {
//        input.input.ki.dwFlags = new WinDef.DWORD(2);  // keyup
//        User32.INSTANCE.SendInput(new WinDef.DWORD(1), (WinUser.INPUT[]) input.toArray(1), input.size());
//    }
//
//    private static WinUser.INPUT pressKey(short vkCode) {
//        WinUser.INPUT input = new WinUser.INPUT();
//        input.type = new WinDef.DWORD(WinUser.INPUT.INPUT_KEYBOARD);
//        input.input.setType("ki");
//        input.input.ki.wScan = new WinDef.WORD(0);
//        input.input.ki.time = new WinDef.DWORD(0);
//        input.input.ki.dwExtraInfo = new BaseTSD.ULONG_PTR(0);
//        input.input.ki.wVk = new WinDef.WORD(vkCode);
//        input.input.ki.dwFlags = new WinDef.DWORD(0);  // keydown
//        return input;
//    }
//
//    private static short getVkCode(char character) {
//        // 检查输入是否为有效的字母（A-Z）或数字（0-9）
//        if ((character < 'A' || character > 'Z') && (character < '0' || character > '9')) {
//            throw new IllegalArgumentException("传入的参数不是有效的字母或数字！");
//        }
//
//        short keyCode;
//
//        if (Character.isLetter(character)) {
//            // 计算字母按键的虚拟键代码（'A'对应65，以此类推）
//            keyCode = (short)(character - 'A' + 0x41);
//        } else {
//            // 计算数字按键的虚拟键代码（'0'对应48，以此类推）
//            keyCode = (short)(character - '0' + 0x30);
//        }
//
//        return keyCode;
//    }

    public static void pressAndRelease(char character) {
        // 转换为大写字符以便处理
        character = Character.toUpperCase(character);

        // 检查输入是否为有效的字母（A-Z）或数字（0-9）
        if ((character < 'A' || character > 'Z') && (character < '0' || character > '9')) {
            System.out.println("传入的参数不是有效的字母或数字！");
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
            System.out.println("无法创建或使用Robot对象：" + e.getMessage());
        }
    }
}
