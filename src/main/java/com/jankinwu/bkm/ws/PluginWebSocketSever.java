package com.jankinwu.bkm.ws;

import com.jankinwu.bkm.enums.PluginEnum;
import com.jankinwu.bkm.hints.PluginWebSocketSeverRuntimeHints;
import com.jankinwu.bkm.pojo.dto.PushMsgDTO;
import jakarta.websocket.*;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ImportRuntimeHints;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author jankinwu
 * @date 2022/5/23 16:27
 * @description WebSocket操作类
 */
@ServerEndpoint("/websocket/plugin/{pluginCode}")
@Component
@Slf4j
@EqualsAndHashCode
@ImportRuntimeHints({PluginWebSocketSeverRuntimeHints.class})
public class PluginWebSocketSever {

    // session集合,存放对应的session
    private static ConcurrentHashMap<Integer, Session> sessionPool = new ConcurrentHashMap<>();
    // concurrent包的线程安全Set,用来存放每个客户端对应的WebSocket对象。
    private static CopyOnWriteArraySet<PluginWebSocketSever> webSocketSet = new CopyOnWriteArraySet<>();
    // 与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    private String HELLO_SERVER = "Hello, Server!";

    /**
     * 建立WebSocket连接
     *
     * @param session Session
     * @param pluginCode 组件编码
     */
    @OnOpen
    public void onOpen(Session session, @PathParam(value = "pluginCode") Integer pluginCode) {
        String pluginName = PluginEnum.getName(pluginCode);
        log.info("WebSocket建立连接中,连接组件名称：{}", pluginName);
        try {
            Session historySession = sessionPool.get(pluginCode);
            if (historySession != null) {
                webSocketSet.remove(historySession);
                historySession.close();
            }
        } catch (IOException e) {
            log.error("重复登录异常,错误信息：" + e.getMessage(), e);
        }
        // 建立连接
        this.session = session;
        webSocketSet.add(this);
        sessionPool.put(pluginCode, session);
        log.info("建立连接完成,当前在线组件数为：{}", webSocketSet.size());
    }

    /**
     * 发生错误
     *
     * @param throwable e
     */
    @OnError
    public void onError(Throwable throwable) {
//        sendAllMessage("连接异常");
        log.error("与插件连接异常", throwable);
    }

    /**
     * 连接关闭
     */
    @OnClose
    public void onClose(@PathParam(value = "pluginCode") Integer pluginCode) {
        webSocketSet.remove(this);
        Session session = sessionPool.remove(pluginCode);
        if (session != null) {
            try (Session currentSession = session) {
                log.info("与{}插件会话关闭，sessionId: {}", PluginEnum.getName(pluginCode), currentSession.getId());
            } catch (IOException e) {
                log.error("Error closing WebSocket session", e);
            }
        }
        log.info("连接断开,当前在线组件数为：{}", webSocketSet.size());
    }

    /**
     * 接收客户端消息
     *
     * @param message 接收的消息
     */
    @OnMessage
    public void onMessage(String message, @PathParam(value = "pluginCode") Integer pluginCode) {
        log.info("收到客户端发来的消息：{}", message);
        sendMessageByPlugin(pluginCode, "Hello, Client!");
    }

    /**
     * 推送消息到指定插件
     *
     * @param pluginCode  插件ID
     * @param message 发送的消息
     */
    public void sendMessageByPlugin(Integer pluginCode, String message) {
        String pluginName = PluginEnum.getName(pluginCode);
        Session session = sessionPool.get(pluginCode);
        if (Objects.isNull(session)) {
            return;
        }
        try {
            session.getBasicRemote().sendText(message);
            log.info("Push message to " + pluginName + ", message：" + message);
        } catch (IOException e) {
            log.error("推送消息到指定组件发生错误：" + e.getMessage(), e);
        }
    }

    /**
     * 推送消息到指定插件
     *
     * @param pluginCode  插件ID
     * @param dto 发送的消息实体
     */
    public void sendMessageByPlugin(Integer pluginCode, PushMsgDTO dto) {
        String pluginName = PluginEnum.getName(pluginCode);
        log.info("推送组件：{}，推送内容：{}", pluginName, dto.getText());
        Session session = sessionPool.get(pluginCode);
        if (Objects.isNull(session)) {
            return;
        }
        try {
            session.getBasicRemote().sendText(dto.toJsonString());

        } catch (IOException e) {
            log.error("推送消息到指定组件发生错误：" + e.getMessage(), e);
        }
    }

    /**
     * 群发消息
     *
     * @param message 发送的消息
     */
    public void sendAllMessage(String message) {
        log.info("发送消息：{}", message);
        for (PluginWebSocketSever webSocket : webSocketSet) {
            try {
                webSocket.session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                log.error("群发消息发生错误：" + e.getMessage(), e);
            }
        }
    }

    public boolean isConnecting(Integer pluginCode) {
        Session session = sessionPool.get(pluginCode);
        return Objects.nonNull(session);
    }

}

