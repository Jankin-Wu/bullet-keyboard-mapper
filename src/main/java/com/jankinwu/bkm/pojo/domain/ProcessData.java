package com.jankinwu.bkm.pojo.domain;

import lombok.Data;

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
}
