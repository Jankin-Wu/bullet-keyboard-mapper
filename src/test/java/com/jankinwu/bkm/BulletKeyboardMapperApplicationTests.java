package com.jankinwu.bkm;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.awt.*;

@SpringBootTest
class BulletKeyboardMapperApplicationTests {

    @Test
    void contextLoads() throws AWTException {
        System.setProperty("java.awt.headless", "false");
    }
}


