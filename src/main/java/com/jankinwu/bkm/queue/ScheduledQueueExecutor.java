package com.jankinwu.bkm.queue;

import com.jankinwu.bkm.executors.AsyncTaskExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author jankinwu
 * @description 定时队列
 * @date 2024/3/3 23:57
 */
@Component
public class ScheduledQueueExecutor {

    private final BlockingQueue<Runnable> queue;

    @Autowired
    private AsyncTaskExecutor asyncTaskExecutor;

    private final RejectedExecutionHandler rejectionHandler;

    private final Lock lock = new ReentrantLock();

    @Autowired
    public ScheduledQueueExecutor(@Value("${app.scheduled.queue-capacity}") int queueCapacity,
                                  @Qualifier("customRejectionPolicy") RejectedExecutionHandler rejectionHandler) {
        this.queue = new LinkedBlockingQueue<>(queueCapacity);
        this.rejectionHandler = rejectionHandler;
    }

    public void addToQueue(Runnable task) {
        boolean added = queue.offer(task);
        if (!added) {
            // 执行拒绝策略
            rejectionHandler.rejectedExecution(task, asyncTaskExecutor);
        }
    }


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
