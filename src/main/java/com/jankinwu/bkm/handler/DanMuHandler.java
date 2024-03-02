package com.jankinwu.bkm.handler;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.jankinwu.bkm.config.BasicConfig;
import com.jankinwu.bkm.enums.KeySimMethodEnum;
import com.jankinwu.bkm.hints.DanMuHandlerRuntimeHints;
import com.jankinwu.bkm.hints.KeyboardSimuRuntimeHints;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ImportRuntimeHints;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
@Slf4j
@Component
@ImportRuntimeHints({DanMuHandlerRuntimeHints.class, KeyboardSimuRuntimeHints.class})
public class DanMuHandler {

    private final Map<String, String> map = new HashMap<>();
    public static final String FILE_NAME = "keyMapping.json";

    @Autowired
    private BasicConfig basicConfig;

    public void handleDanMu(String content) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        initMap();
        JSONObject contentJson = JSONObject.parseObject(content);
        JSONObject data = contentJson.getJSONObject("data");
        String msg = data.getString("msg");
        String uname = data.getString("uname");
        log.info("[弹幕] {}: {}", uname, msg);
        if (basicConfig.getIgnoreCase()) {
            msg = msg.toUpperCase(Locale.ROOT);
        }
        String key = map.get(msg);
        if (StrUtil.isNotBlank(key)) {
            // 获取相应模拟键盘工具类并触发按键
            Class<?> simUtils = KeySimMethodEnum.getSimUtils(basicConfig.getSimMethod());
            if (Objects.nonNull(simUtils)) {
                Method method = simUtils.getMethod("pressAndRelease", String.class);
                method.invoke(null, key);
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
            File externalFile = new File(basicConfig.getMappingFilePath());
            // 检查外部目录是否存在keyMapping.json文件，如果有就使用外部的映射文件
            if (externalFile.exists()) {
                try (InputStream inputStream = new FileInputStream(externalFile)) {
                    readJson(inputStream);
                } catch (Exception e) {
                    log.info("映射文件解析异常");
                    e.printStackTrace();
                }
            } else {
                log.info("检测不到外部映射文件，开始加载内部默认映射文件。。。");
                try (InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(FILE_NAME)) {
                    if (Objects.nonNull(inputStream)) {
                        readJson(inputStream);
                    }
                } catch (Exception e) {
                    log.info("找不到映射文件");
                }
            }
        }
    }

    private void readJson(InputStream inputStream) throws IOException {
        log.info("加载按键映射...");
        byte[] bytes = inputStream.readAllBytes();
        String jsonString = new String(bytes, StandardCharsets.UTF_8);

        JSONArray jsonArray = JSON.parseArray(jsonString);
        if (jsonArray.size() <= 0) {
            log.info("按键映射读取失败");
        }
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String msg = jsonObject.getString("msg");
            String key = jsonObject.getString("key");
            if (StrUtil.isNotBlank(msg) && StrUtil.isNotBlank(key)) {
                map.put(msg, key);
            }
        }
        log.info("按键映射加载完毕");
    }
}
