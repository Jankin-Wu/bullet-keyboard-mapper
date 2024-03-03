package com.jankinwu.bkm.handler;

import com.jankinwu.bkm.pojo.dto.RequestProcessContext;
import com.jankinwu.bkm.queue.DelayedTaskManager;
import com.jankinwu.bkm.queue.ScheduledQueueExecutor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author wwg
 * @description 延时队列处理
 * @date 2024/3/3 22:33
 */
@Service
@RequiredArgsConstructor
public class DelayTaskHandlerChain extends AbstractBulletCommentHandlerChain {

    private final DelayedTaskManager delayedTaskManager;

    private final ScheduledQueueExecutor scheduledQueueExecutor;

    @Override
    public void doChain(RequestProcessContext context) {
        addDelayedTask(context);
    }

    private void addDelayedTask(RequestProcessContext context) {
        Runnable task = () -> {
            if (getNext() != null) {
                getNext().doChain(context);
            }
        };
        scheduledQueueExecutor.addToQueue(task);
//        long delayTime = basicConfig.getDelayTime();
//        DelayedTask delayedTask = new DelayedTask(delayTime, task);
//        delayedTaskManager.addDelayedTask(delayedTask);
    }
}
