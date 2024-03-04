package com.jankinwu.bkm.queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * @author jankinwu
 * @description 延时任务管理
 * @date 2024/3/3 22:29
 */
@Component
public class DelayedTaskManager {

    private final ScheduledExecutorService executorService;
    private final DelayQueue<DelayedTask> delayQueue;

    @Autowired
    public DelayedTaskManager() {
        executorService = new ScheduledThreadPoolExecutor(1);
        delayQueue = new DelayQueue<>();
    }

    // 添加延时任务
    public void addDelayedTask(DelayedTask task) {
        delayQueue.offer(task);
    }

    // 定时检查并执行到期的任务
    @Scheduled(fixedDelay = 1000) // 每秒钟检查一次
    public void checkDelayedTasks() {
        while (!delayQueue.isEmpty()) {
            DelayedTask task = delayQueue.poll();
            if (task != null) {
                task.execute();
            }
        }
    }
}
