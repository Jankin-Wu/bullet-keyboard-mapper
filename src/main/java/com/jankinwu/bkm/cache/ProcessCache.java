package com.jankinwu.bkm.cache;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.jankinwu.bkm.config.AppConfig;
import com.jankinwu.bkm.executors.AsyncTaskExecutor;
import com.jankinwu.bkm.pojo.domain.ProcessData;
import com.jankinwu.bkm.pojo.domain.Stage;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author jankinwu
 * @description 缓存执行流程
 * @date 2024/3/3 15:33
 */
@Getter
@Slf4j
@RequiredArgsConstructor
@Component
public class ProcessCache {

    private final AppConfig appConfig;

    private Map<String, ProcessData> processMap;

    private final AsyncTaskExecutor executor;

    @PostConstruct
    public void init() {
        executor.execute(this::loadProcess);
    }

    private void loadProcess() {
        if (Objects.isNull(appConfig)) {
            log.error("配置文件获取失败");
        }
        log.info("开始加载按键执行计划。。。");
        String processDir = appConfig.getProcessDir();
        if (StrUtil.isBlank(processDir)) {
            log.error("执行计划目录不能为空");
        }
        Path path = Path.of(processDir);
        try (Stream<Path> paths = Files.walk(path)) {
            log.info("执行计划目录：{}", path);
            this.processMap = paths
                    .filter(Files::isRegularFile)
                    .filter(p -> p.toString().endsWith(".json"))
                    .flatMap(p -> {
                        try {
                            String content = Files.readString(p);
                            List<ProcessData> processesInFile;
                            try {
                                processesInFile = ProcessData.parseJsonArray(content);
                            } catch (Exception e) {
                                throw new RuntimeException("process文件解析异常，请检查文件内容格式是否正确，请修正后重启应用。");
                            }
                            processesInFile.forEach(process -> process.getStages().sort(Comparator.comparingInt(Stage::getOrder)));
                            return processesInFile.stream();
                        } catch (IOException e) {
                            throw new UncheckedIOException(e);
                        }
                    }).collect(Collectors.toMap(
                    ProcessData::getProcessName,
                    Function.identity(),
                    (existingValue, newValue) -> {
                        log.warn("存在相同的 ProcessName： {}", existingValue.getProcessName());
                        return existingValue;
                    }
            ));
        } catch (IOException ex) {
            log.error("按键执行计划加载失败: {}", ex.getMessage());
        }
        log.info("按键执行计划加载完成！已加载执行计划数量：{}", processMap.size());
    }
}
