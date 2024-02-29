package handler;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import config.GlobalConfigHolder;
import enums.KeyMappingEnum;
import utils.KeyboardSimulationUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;


/**
 * @author jankinwu
 * @description 弹幕处理器
 * @date 2024/2/26 23:34
 */
public class DanMuHandler {

    private final Map<String, String> map = new HashMap<>();
    private final String FILE_NAME = "keyMapping.json";
    private final String EXTERNAL_FILE_PATH = "./keyMapping.json";


    public void handleDanMu(String content) {
        initMap();
        JSONObject contentJson = JSONObject.parseObject(content);
        JSONObject data = contentJson.getJSONObject("data");
        String msg = data.getString("msg");
        String uname = data.getString("uname");
        System.out.println(DateUtil.now() + " [弹幕] " + uname + "：" + msg);
        if (GlobalConfigHolder.getBasicConfig().getIgnoreCase()) {
            msg = msg.toUpperCase(Locale.ROOT);
        }
        String key = map.get(msg);
        if (StrUtil.isNotBlank(key)) {
            Integer eventCode = KeyMappingEnum.getEventCode(key);
            if (Objects.nonNull(eventCode)) {
                System.out.println(DateUtil.now() + " 模拟按键：" + key);
                KeyboardSimulationUtils.pressAndRelease(eventCode);
            }
        }
    }

    public void initMap() {
        if (CollUtil.isNotEmpty(map)) {
            return;
        }
        // 使用双检锁避免高并发时重复初始化map
        synchronized (this) {
            if (CollUtil.isNotEmpty(map)) {
                return;
            }
            File externalFile = new File(EXTERNAL_FILE_PATH);
            // 检查外部目录是否存在keyMapping.json文件，如果有就使用外部的映射文件
            if (externalFile.exists()) {
                try (InputStream inputStream = new FileInputStream(EXTERNAL_FILE_PATH)) {
                    readJson(inputStream);
                } catch (Exception e) {
                    System.out.println(DateUtil.now() + " 映射文件解析异常");
                    e.printStackTrace();
                }
            } else {
                System.out.println("检测不到外部映射文件，开始加载内部默认映射文件。。。");
                try (InputStream inputStream = DanMuHandler.class.getClassLoader().getResourceAsStream(FILE_NAME)) {
                    if (Objects.nonNull(inputStream)) {
                        readJson(inputStream);
                    }
                } catch (Exception e) {
                    System.out.println(DateUtil.now() + " 找不到映射文件");
                }
            }
        }
    }

    private void readJson(InputStream inputStream) throws IOException {
        System.out.println(DateUtil.now() + " 加载按键映射...");
        byte[] bytes = inputStream.readAllBytes();
        String jsonString = new String(bytes, StandardCharsets.UTF_8);

        JSONArray jsonArray = JSON.parseArray(jsonString);
        if (jsonArray.size() <= 0) {
            System.out.println(DateUtil.now() + " 按键映射读取失败");
        }
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String msg = jsonObject.getString("msg");
            String key = jsonObject.getString("key");
            if (StrUtil.isNotBlank(msg) && StrUtil.isNotBlank(key)) {
                map.put(msg, key);
            }
        }
        System.out.println(DateUtil.now() + " 按键映射加载完毕");
    }
}
