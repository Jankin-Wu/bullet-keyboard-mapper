package com.jankinwu.bkm.handler;

import com.jankinwu.bkm.pojo.dto.RequestProcessContext;
import com.jankinwu.bkm.queue.DelayedTaskManager;
import com.jankinwu.bkm.queue.ScheduledQueueExecutor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.RejectedExecutionException;

/**
 * @author jankinwu
 * @description 延时队列处理
 * @date 2024/3/3 22:33
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class DelayTaskHandlerChain extends AbstractBulletCommentHandlerChain {

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
        try {
            scheduledQueueExecutor.addToQueue(task);
        } catch (RejectedExecutionException e) {
            log.warn("队列已满，用户[{}]的任务[{}]被抛弃", context.getRequest().getData().getUname(), context.getProcess().getProcessName());
        }
        // 插入延迟队列
//        long delayTime = basicConfig.getDelayTime();
//        DelayedTask delayedTask = new DelayedTask(delayTime, task);
//        delayedTaskManager.addDelayedTask(delayedTask);
    }
}
