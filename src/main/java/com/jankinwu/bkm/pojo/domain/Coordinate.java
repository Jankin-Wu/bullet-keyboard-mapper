package com.jankinwu.bkm.pojo.domain;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSONObject;
import lombok.Data;

/**
 * @author jankinwu
 * @description 鼠标点击坐标
 * @date 2024/3/14 23:26
 */
@Data
public class Coordinate {

    private int x;

    private int y;

    public static Coordinate parseCoordinate(String coordinateJson) {
        Coordinate coordinate =new Coordinate();
        if (StrUtil.isNotBlank(coordinateJson)) {
            JSONObject coordinateObject = JSONObject.parseObject(coordinateJson);
            coordinate.setX(coordinateObject.getIntValue("x"));
            coordinate.setY(coordinateObject.getIntValue("y"));
            return coordinate;
        }
        return coordinate;
    }
}
