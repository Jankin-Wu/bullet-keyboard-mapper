package com.jankinwu.bkm.hints;

import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHintsRegistrar;
import org.springframework.stereotype.Component;

/**
 * @author jankinwu
 * @description
 * @date 2024/3/2 1:14
 */
@Component
public class DanMuHandlerRuntimeHints implements RuntimeHintsRegistrar {

    @Override
    public void registerHints(RuntimeHints hints, ClassLoader classLoader) {
        hints.resources().registerPattern("keyMapping.json");
    }
}
