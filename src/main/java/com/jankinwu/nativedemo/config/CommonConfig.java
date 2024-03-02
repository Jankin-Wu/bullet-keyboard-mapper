package com.jankinwu.nativedemo.config;

import com.jankinwu.nativedemo.hints.DanMuHandlerRuntimeHints;
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
    public DanMuHandlerRuntimeHints hintsRegistrar() {
        return new DanMuHandlerRuntimeHints();
    }
}
