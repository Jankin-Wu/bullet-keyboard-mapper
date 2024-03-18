package com.jankinwu.bkm.pojo.domain;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
                if (stageObject.getIntValue("order") != 0) {
                    stage.setOrder(stageObject.getIntValue("order"));
                }
                stage.setName(stageObject.getString("name"));
                if (stageObject.getIntValue("intervalBefore") >= 0) {
                    stage.setRepeatInterval(stageObject.getIntValue("intervalBefore"));
                }else {
                    throw new RuntimeException("非法参数，intervalBefore 只能为0或正整数");
                }
                if (stageObject.getIntValue("intervalAfter") >= 0) {
                    stage.setRepeatInterval(stageObject.getIntValue("intervalAfter"));
                }else {
                    throw new RuntimeException("非法参数，intervalAfter 只能为0或正整数");
                }
                if (stageObject.getIntValue("repeatInterval") > 0) {
                    stage.setRepeatInterval(stageObject.getIntValue("repeatInterval"));
                }else if (stageObject.getIntValue("repeatInterval") < 0){
                    throw new RuntimeException("非法参数，repeatInterval 只能为0或正整数");
                }
                if (stageObject.getIntValue("repeatTimes") > 0) {
                    stage.setRepeatTimes(stageObject.getIntValue("repeatTimes"));
                } else if (stageObject.getIntValue("repeatTimes") < 0){
                    throw new RuntimeException("非法参数，repeatTimes 只能为0或正整数");
                }
                if (stageObject.getIntValue("holdTime") >= 0) {
                    stage.setRepeatInterval(stageObject.getIntValue("holdTime"));
                } else {
                    throw new RuntimeException("非法参数，holdTime 只能为0或正整数");
                }
                stage.setMouse(stageObject.getBooleanValue("isMouse"));
                if (Objects.nonNull(stageObject.getJSONObject("coordinate"))) {
                    stage.setCoordinate(Coordinate.parseCoordinate(stageObject.getJSONObject("coordinate").toJSONString()));
                }
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
