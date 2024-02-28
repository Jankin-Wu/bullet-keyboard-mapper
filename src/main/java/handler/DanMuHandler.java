package handler;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;
import utils.KeyboardSimulationUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Map;


/**
 * @author jankinwu
 * @description 弹幕处理器
 * @date 2024/2/26 23:34
 */
@Slf4j
public class DanMuHandler {

    private Map<String, Character> map;
    private final String FILE_NAME = "keyMapping.json";

    public void handleDanMu(String content) {
        initMap();
        JSONObject contentJson = JSONObject.parseObject(content);
        JSONObject data = contentJson.getJSONObject("data");
        String msg = data.getString("msg");
        String uname = data.getString("uname");
        log.info("[弹幕] {}：{}", uname, msg);
        map.forEach((key, value) -> {
            if (key.equals(msg)) {
                KeyboardSimulationUtils.pressAndRelease(value);
            }
        });
    }

    private void initMap() {
        try (InputStream inputStream = DanMuHandler.class.getClassLoader().getResourceAsStream(FILE_NAME)) {
            if (inputStream != null) {
                byte[] bytes = inputStream.readAllBytes();
                String jsonString = new String(bytes, StandardCharsets.UTF_8);

                JSONArray jsonArray = JSON.parseArray(jsonString);
                for (int i = 0; i < jsonArray.size(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String msg = jsonObject.getString("msg");
                    String key = jsonObject.getString("key");
                    if (msg != null && key != null && key.length() == 1) {
                        map.put(msg, key.charAt(0));
                    }
                }
            } else {
                System.out.println("File not found: " + FILE_NAME);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
