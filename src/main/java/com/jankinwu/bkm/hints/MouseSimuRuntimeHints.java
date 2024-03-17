package com.jankinwu.bkm.hints;

import com.jankinwu.bkm.utils.MouseSimulationJnaUtils;
import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHintsRegistrar;

/**
 * @author jankinwu
 * @description
 * @date 2024/3/17 20:23
 */
public class MouseSimuRuntimeHints implements RuntimeHintsRegistrar {

    @Override
    public void registerHints(RuntimeHints hints, ClassLoader classLoader) {
        hints.proxies().registerJdkProxy(MouseSimulationJnaUtils.User32.class);
    }
}
