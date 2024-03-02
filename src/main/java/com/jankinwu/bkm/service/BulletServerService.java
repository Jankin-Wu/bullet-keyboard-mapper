package com.jankinwu.bkm.service;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.jankinwu.bkm.config.BasicConfig;
import com.jankinwu.bkm.handler.ConfigHandler;
import com.jankinwu.bkm.handler.DanMuHandler;
import com.jankinwu.bkm.listener.WebsocketListener;
import com.jankinwu.bkm.request.ProjectRequest;
import jakarta.annotation.PostConstruct;
import jakarta.websocket.ContainerProvider;
import jakarta.websocket.WebSocketContainer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.Objects;

/**
 * @author jankinwu
 * @description 弹幕服务
 * @date 2024/2/28 1:26
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class BulletServerService {

    public final BasicConfig basicConfig;

    private final DanMuHandler handler;

    private final ConfigHandler configHandler;

    @PostConstruct
    public void run() {
        try {
//            BulletService.basicConfig = ConfigUtils.getConfig("config-dev.yml", BasicConfig.class);
            if (Objects.isNull(basicConfig)) {
                throw new RuntimeException("读取不到配置");
            }
            configHandler.verifyConfig(basicConfig);
            ProjectRequest p = new ProjectRequest(basicConfig.getAppId(), basicConfig.getAccessKey(), basicConfig.getAccessSecret());
            //获取弹幕服务信息
            String result = p.start(basicConfig.getIdCode());
//            log.info("弹幕服务信息：{}", result);
            log.info("弹幕服务信息：" + result);
            JSONObject data = JSONObject.parseObject(result).getJSONObject("data");
            if (Objects.isNull(data)) {
                throw new RuntimeException(Objects.requireNonNull(JSONObject.parseObject(result)).getString("message"));
            }
            //个人信息
            JSONObject anchorInfo = data.getJSONObject("anchor_info");
            //弹幕服务器信息
            JSONObject websocketInfo = data.getJSONObject("websocket_info");
            //弹幕服务器地址
            JSONArray wssLinks = websocketInfo.getJSONArray("wss_link");
            //websocket鉴权信息
            String authBody = websocketInfo.getString("auth_body");
            //选一个服务器节点
            String uri = wssLinks.getString(0);
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            // 连接到WebSocket服务器
            container.connectToServer(new WebsocketListener(authBody, handler), new URI(uri));
        } catch (Exception e) {
//            ErrorDialog.displayErrorMessage(e.getMessage());
            log.error("运行异常：{}", e.getMessage());
            // 在点击对话框确定按钮后退出应用程序
//            System.exit(1);
        }
    }
}
