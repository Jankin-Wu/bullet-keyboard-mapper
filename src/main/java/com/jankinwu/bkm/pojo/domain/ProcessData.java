package com.jankinwu.bkm.pojo.domain;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jankinwu
 * @description 执行计划
 * @date 2024/3/3 4:17
 */
@Data
public class ProcessData {

    private String processName;

    private List<Stage> stages;

    /**
     * 由于使用native方式打包时候不支持反射，这里采用手动解析的方式
     *
     * @param content process content
     * @return List<ProcessData>
     */
    public static List<ProcessData> parseJsonArray(String content) {
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
                stage.setCoordinate(Coordinate.parseCoordinate(stageObject.getJSONObject("coordinate") == null? "" : stageObject.getJSONObject("coordinate").toJSONString()));
                stage.setScroll(stageObject.getBooleanValue("isScroll"));
                stage.setScrollAmount(stageObject.getIntValue("scrollAmount"));
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
