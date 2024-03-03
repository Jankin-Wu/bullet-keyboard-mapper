package com.jankinwu.bkm.queue;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author wwg
 * @description
 * @date 2024/3/4 0:23
 */
@Component
public class AsyncTaskExecutor extends ThreadPoolExecutor {

    public AsyncTaskExecutor(
            @Value("${app.scheduled.queue-core-pool-size:1}") int corePoolSize,
            @Value("${app.scheduled.queue-max-pool-size:100}") int maxPoolSize,
            @Value("${app.scheduled.queue-keep-alive-seconds:60}") int keepAliveSeconds,
            @Qualifier("taskQueue") BlockingQueue<Runnable> workQueue
    ) {
        super(corePoolSize, maxPoolSize, keepAliveSeconds, TimeUnit.SECONDS, workQueue);
    }

}
