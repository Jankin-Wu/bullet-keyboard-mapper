package com.jankinwu.bkm.hints;

import com.jankinwu.bkm.utils.KeyboardSimulationAwtUtils;
import com.jankinwu.bkm.utils.KeyboardSimulationJnaUtils;
import org.springframework.aot.hint.ExecutableMode;
import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHintsRegistrar;

/**
 * @author jankinwu
 * @description
 * @date 2024/3/2 13:20
 */
public class KeyboardSimuRuntimeHints implements RuntimeHintsRegistrar {

    @Override
    public void registerHints(RuntimeHints hints, ClassLoader classLoader) {
        try {
            hints.reflection().registerMethod(KeyboardSimulationAwtUtils.class.getMethod("pressAndRelease", String.class), ExecutableMode.INVOKE);
            hints.reflection().registerMethod(KeyboardSimulationJnaUtils.class.getMethod("pressAndRelease", String.class), ExecutableMode.INVOKE);
            hints.reflection().registerMethod(KeyboardSimulationJnaUtils.class.getMethod("press", String.class), ExecutableMode.INVOKE);
            hints.reflection().registerMethod(KeyboardSimulationJnaUtils.class.getMethod("release", String.class), ExecutableMode.INVOKE);
            hints.proxies().registerJdkProxy(KeyboardSimulationJnaUtils.User32.class);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
