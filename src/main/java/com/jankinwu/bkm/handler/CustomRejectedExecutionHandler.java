package com.jankinwu.bkm.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author jankinwu
 * @description 任务拒绝策略
 * @date 2024/3/4 1:06
 */
@Slf4j
@Component
public class CustomRejectedExecutionHandler implements RejectedExecutionHandler {

    public static final String ABORT_NEW = "abort-new";
    @Value("${app.scheduled.rejection-policy:abort-new}")
    private String rejectionPolicy;

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        RejectedExecutionHandler rejectedExecutionHandler;
        if (ABORT_NEW.equals(rejectionPolicy)) {
            rejectedExecutionHandler = new ThreadPoolExecutor.AbortPolicy();
        } else {
            rejectedExecutionHandler = new ThreadPoolExecutor.DiscardOldestPolicy();
        }
        rejectedExecutionHandler.rejectedExecution(r, executor);

    }
}
