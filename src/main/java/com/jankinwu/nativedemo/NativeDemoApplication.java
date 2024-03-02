package com.jankinwu.nativedemo;

import com.jankinwu.nativedemo.config.CommonConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(CommonConfig.class)
public class NativeDemoApplication {
    public static void main(String[] args) {
        System.setProperty("java.home", ".");
        System.setProperty("java.awt.headless", "false");
//        System.out.println(System.getProperty("java.library.path"));
//        System.setProperty("")
//        System.setProperty("java.library.path", ".");
//        System.loadLibrary("awt");
        SpringApplication.run(NativeDemoApplication.class, args);
    }
}
