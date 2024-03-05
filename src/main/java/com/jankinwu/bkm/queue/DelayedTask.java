package com.jankinwu.bkm.queue;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author jankinwu
 * @description 延迟任务类
 * @date 2024/3/3 22:27
 */
public class DelayedTask implements Delayed {

    // 要执行的任务
    private final Runnable task;

    // 任务执行时间
    private final long executeTime;

    public DelayedTask(long delayTime, Runnable task) {
        // 延迟时间
        this.task = task;
        this.executeTime = System.currentTimeMillis() + delayTime;
    }

    public void execute() {
        task.run();
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(executeTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed other) {
        if (this == other) {
            return 0;
        }
        long diff = this.getDelay(TimeUnit.MILLISECONDS) - other.getDelay(TimeUnit.MILLISECONDS);
        return Long.compare(diff, 0);
    }
}
