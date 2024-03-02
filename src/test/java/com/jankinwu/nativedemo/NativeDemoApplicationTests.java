package com.jankinwu.nativedemo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.awt.*;
import java.awt.event.KeyEvent;

@SpringBootTest
class NativeDemoApplicationTests {

    @Test
    void contextLoads() throws AWTException {
        System.setProperty("java.awt.headless", "false");
        Robot robot = new Robot();

        // 模拟按下按键
//        robot.keyPress(KeyEvent.VK_A);
    }

}
