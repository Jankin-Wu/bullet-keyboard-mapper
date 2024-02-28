package service;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import config.BasicConfig;
import jakarta.websocket.ContainerProvider;
import jakarta.websocket.WebSocketContainer;
import listener.WebsocketListener;
import request.ProjectRequest;
import utils.ConfigUtils;

import java.net.URI;
import java.util.Objects;

/**
 * @author jankinwu
 * @description 弹幕服务
 * @date 2024/2/28 1:26
 */
public class BulletService {

    public static BasicConfig basicConfig;

    public static void requestServer() {
        try {
            basicConfig = ConfigUtils.getConfig("config-dev.yml", BasicConfig.class);
            if (Objects.isNull(basicConfig)) {
                throw new RuntimeException("读取不到配置");
            }
            ProjectRequest p = new ProjectRequest(basicConfig.getAppId(), basicConfig.getAccessKey(), basicConfig.getAccessSecret());
            //获取弹幕服务信息
            String result = p.start(basicConfig.getIdCode());
//            log.info("弹幕服务信息：{}", result);
            System.out.println(DateUtil.now() + " 弹幕服务信息：" + result);
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
            container.connectToServer(new WebsocketListener(authBody), new URI(uri));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
