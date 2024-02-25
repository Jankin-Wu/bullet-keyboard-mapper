package starter;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import config.BasicConfig;
import jakarta.websocket.ContainerProvider;
import jakarta.websocket.DeploymentException;
import jakarta.websocket.WebSocketContainer;
import listener.WebsocketListener;
import request.ProjectRequest;
import utils.ConfigUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

/**
 * @author jankinwu
 * @description 启动器
 * @date 2024/2/25 13:09
 */
public class Starter {

    public static BasicConfig basicConfig;

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException, InvalidKeyException, URISyntaxException, DeploymentException {
        basicConfig = ConfigUtils.getConfig("config.yml", BasicConfig.class);
        if (Objects.isNull(basicConfig)) {
            throw new RuntimeException("读取不到配置");
        }
        ProjectRequest p = new ProjectRequest(basicConfig.getAppId(), basicConfig.getAccessKey(), basicConfig.getAccessSecret());
        //获取弹幕服务信息
        String result = p.start(basicConfig.getIdCode());
        JSONObject data = JSONObject.parseObject(result).getJSONObject("data");
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
    }

}
