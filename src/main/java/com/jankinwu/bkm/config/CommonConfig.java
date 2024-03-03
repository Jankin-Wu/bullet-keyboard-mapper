package com.jankinwu.bkm.config;

import com.jankinwu.bkm.hints.BulletHandlerRuntimeHints;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author jankinwu
 * @description
 * @date 2024/3/2 1:25
 */
@Configuration
public class CommonConfig {

    @Bean
    public BulletHandlerRuntimeHints hintsRegistrar() {
        return new BulletHandlerRuntimeHints();
    }
}
