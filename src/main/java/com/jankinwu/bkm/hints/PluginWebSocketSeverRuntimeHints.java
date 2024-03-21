package com.jankinwu.bkm.hints;

import com.jankinwu.bkm.ws.PluginWebSocketSever;
import jakarta.websocket.Session;
import org.springframework.aot.hint.ExecutableMode;
import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHintsRegistrar;

/**
 * @author jankinwu
 * @description
 * @date 2024/3/8 12:43
 */
public class PluginWebSocketSeverRuntimeHints implements RuntimeHintsRegistrar {

    @Override
    public void registerHints(RuntimeHints hints, ClassLoader classLoader) {
        try {
            hints.reflection().registerMethod(PluginWebSocketSever.class.getMethod("onOpen", Session.class, Integer.class), ExecutableMode.INVOKE);
            hints.reflection().registerMethod(PluginWebSocketSever.class.getMethod("onClose", Integer.class), ExecutableMode.INVOKE);
            hints.reflection().registerMethod(PluginWebSocketSever.class.getMethod("onError", Throwable.class), ExecutableMode.INVOKE);
            hints.reflection().registerMethod(PluginWebSocketSever.class.getMethod("onMessage", String.class, Integer.class), ExecutableMode.INVOKE);
            hints.reflection().registerConstructor(PluginWebSocketSever.class.getConstructor(), ExecutableMode.INVOKE);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
