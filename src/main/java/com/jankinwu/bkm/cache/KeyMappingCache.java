package com.jankinwu.bkm.cache;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.jankinwu.bkm.config.AppConfig;
import com.jankinwu.bkm.executors.AsyncTaskExecutor;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * @author jankinwu
 * @description 按键映射缓存
 * @date 2024/3/27 0:52
 */
//@Getter
@Slf4j
@RequiredArgsConstructor
@Component
public class KeyMappingCache {

    private final AppConfig appConfig;

    @Getter
    private final Map<String, String> keyProcessMap = new HashMap<>();

    private final AsyncTaskExecutor executor;

    @PostConstruct
    public void init() {
        executor.execute(this::loadKeyMapping);
    }

    public void loadKeyMapping() {
        File externalFile = new File(appConfig.getMappingFilePath());
        // 检查外部目录是否存在keyMapping.json文件，如果有就使用外部的映射文件
        if (externalFile.exists()) {
            try (InputStream inputStream = new FileInputStream(externalFile)) {
                readJson(inputStream);
            } catch (Exception e) {
                log.error("映射文件解析异常", e);
            }
        } else {
            log.error("检测不到按键映射文件");
        }
    }

    private void readJson(InputStream inputStream) throws IOException {
        log.info("加载按键映射...");
        byte[] bytes = inputStream.readAllBytes();
        String jsonString = new String(bytes, StandardCharsets.UTF_8);

        JSONArray jsonArray = JSON.parseArray(jsonString);
        if (jsonArray.isEmpty()) {
            log.info("按键映射读取失败");
        }
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String msg = jsonObject.getString("msg");
            String key = jsonObject.getString("processName");
            String type = jsonObject.getString("type");
            if (StrUtil.isNotBlank(msg) && StrUtil.isNotBlank(key)) {
                String mapKey = type + ":" + msg;
                keyProcessMap.put(mapKey, key);
            }
        }
        log.info("按键映射加载完毕");
    }
}
