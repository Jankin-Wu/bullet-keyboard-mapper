package com.jankinwu.bkm.handler;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author wwg
 * @description 任务拒绝策略
 * @date 2024/3/4 1:06
 */
@Slf4j
public class CustomRejectedExecutionHandler implements RejectedExecutionHandler {
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        log.warn("Task rejected: {}", r.toString());
    }
}
