package com.jankinwu.bkm;

import com.jankinwu.bkm.config.CommonConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(CommonConfig.class)
public class BulletKeyboardMapperApplication {
    public static void main(String[] args) {
        System.setProperty("java.home", ".");
        System.setProperty("java.awt.headless", "false");
//        System.out.println(System.getProperty("java.library.path"));
//        System.setProperty("")
//        System.setProperty("java.library.path", ".");
//        System.loadLibrary("awt");
        SpringApplication.run(BulletKeyboardMapperApplication.class, args);
    }
}
