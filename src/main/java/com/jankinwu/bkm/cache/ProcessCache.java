package com.jankinwu.bkm.cache;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.jankinwu.bkm.config.BasicConfig;
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
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
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

    private final BasicConfig basicConfig;

    private List<ProcessData> processList;

    private final AsyncTaskExecutor executor;

    @PostConstruct
    public void init() {
        executor.execute(this::loadProcess);
    }

    private void loadProcess() {
        if (Objects.isNull(basicConfig)) {
            log.error("配置文件获取失败");
        }
        log.info("开始加载按键执行计划。。。");
        String processDir = basicConfig.getProcessDir();
        if (StrUtil.isBlank(processDir)) {
            log.error("执行计划目录不能为空");
        }
        Path path = Path.of(processDir);
        try (Stream<Path> paths = Files.walk(path)) {
            log.info("执行计划目录：{}", path);
            this.processList = paths
                    .filter(Files::isRegularFile)
                    .filter(p -> p.toString().endsWith(".json"))
                    .flatMap(p -> {
                        try {
                            String content = Files.readString(p);
                            List<ProcessData> processesInFile;
                            try {
                                processesInFile = parseProcessData(content);
                            } catch (Exception e) {
                                throw new RuntimeException("process文件解析异常，请检查文件内容格式是否正确，请修正后重启应用。");
                            }
                            processesInFile.forEach(process -> process.getStages().sort(Comparator.comparingInt(Stage::getOrder)));
                            return processesInFile.stream();
                        } catch (IOException e) {
                            throw new UncheckedIOException(e);
                        }
                    }).toList();
        } catch (IOException ex) {
            log.error("按键执行计划加载失败: {}", ex.getMessage());
        }
        log.info("按键执行计划加载完成！已加载执行计划数量：{}", processList.size());
    }

    /**
     * 由于使用native方式打包时候不支持反射，这里采用手动解析的方式
     *
     * @param content process content
     * @return List<ProcessData>
     */
    private List<ProcessData> parseProcessData(String content) {
        JSONArray jsonArray = JSON.parseArray(content);
        List<ProcessData> processDataList = new ArrayList<>();

        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            ProcessData processData = new ProcessData();
            processData.setProcessName(jsonObject.getString("processName"));

            JSONArray stagesArray = jsonObject.getJSONArray("stages");
            List<Stage> stageList = new ArrayList<>();

            for (int j = 0; j < stagesArray.size(); j++) {
                JSONObject stageObject = stagesArray.getJSONObject(j);
                Stage stage = new Stage();
                stage.setOrder(stageObject.getIntValue("order"));
                stage.setName(stageObject.getString("name"));
                stage.setIntervalBefore(stageObject.getIntValue("intervalBefore"));
                stage.setIntervalAfter(stageObject.getIntValue("intervalAfter"));
                stage.setRepeatInterval(stageObject.getIntValue("repeatInterval"));
                stage.setRepeatTimes(stageObject.getIntValue("repeatTimes"));
                stage.setHoldTime(stageObject.getIntValue("holdTime"));
                stage.setMouse(stageObject.getBooleanValue("isMouse"));

                JSONArray keysArray = stageObject.getJSONArray("keys");
                List<String> keysList = new ArrayList<>();
                for (int k = 0; k < keysArray.size(); k++) {
                    keysList.add(keysArray.getString(k));
                }
                stage.setKeys(keysList);

                stageList.add(stage);
            }

            processData.setStages(stageList);
            processDataList.add(processData);
        }
        return processDataList;
    }
}
