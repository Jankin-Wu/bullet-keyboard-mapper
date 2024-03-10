package com.jankinwu.bkm.hints;

import com.jankinwu.bkm.ws.BulletCommentWebsocketListener;
import jakarta.websocket.CloseReason;
import jakarta.websocket.Session;
import org.springframework.aot.hint.ExecutableMode;
import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHintsRegistrar;

import java.nio.ByteBuffer;

/**
 * @author jankinwu
 * @description
 * @date 2024/3/2 13:20
 */
public class WebsocketListenerRuntimeHints implements RuntimeHintsRegistrar {

    @Override
    public void registerHints(RuntimeHints hints, ClassLoader classLoader) {
        try {
            hints.reflection().registerMethod(BulletCommentWebsocketListener.class.getMethod("onOpen", Session.class), ExecutableMode.INVOKE);
            hints.reflection().registerMethod(BulletCommentWebsocketListener.class.getMethod("onClose", Session.class, CloseReason.class), ExecutableMode.INVOKE);
            hints.reflection().registerMethod(BulletCommentWebsocketListener.class.getMethod("onError", Session.class, Throwable.class), ExecutableMode.INVOKE);
            hints.reflection().registerMethod(BulletCommentWebsocketListener.class.getMethod("onMessage", ByteBuffer.class), ExecutableMode.INVOKE);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
