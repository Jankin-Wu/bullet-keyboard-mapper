package com.jankinwu.bkm.pojo.domain;

import lombok.Data;

import java.util.List;

/**
 * @author jankinwu
 * @description 执行步骤
 * @date 2024/3/3 13:31
 */
@Data
public class Stage {

    private int order = 1;

    private String name;

    private int intervalBefore = 0;

    private int intervalAfter = 0;

    private int repeatInterval = 100;

    private int repeatTimes = 1;

    private int holdTime = 0;

    private boolean isMouse = false;

    private List<String> keys;
}
