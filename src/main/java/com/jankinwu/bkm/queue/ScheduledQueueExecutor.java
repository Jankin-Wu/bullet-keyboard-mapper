package com.jankinwu.bkm.queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wwg
 * @description 定时队列
 * @date 2024/3/3 23:57
 */
@Component
@EnableAsync
public class ScheduledQueueExecutor {
    private BlockingQueue<Runnable> queue = new LinkedBlockingQueue<>();

    @Autowired
    private AsyncTaskExecutor asyncTaskExecutor;

    private Lock lock = new ReentrantLock();

    public void addToQueue(Runnable task) {
        queue.offer(task);
    }

    @Async("asyncTaskExecutor")
    @Scheduled(fixedDelayString = "${app.scheduled.execution-interval}")
    public void processQueue() {
        if (lock.tryLock()) {
            try {
                Runnable task = queue.poll();
                if (task != null) {
                    // 执行任务
                    task.run();
                }
            } finally {
                lock.unlock();
            }
        }
    }
}
