package com.jankinwu.bkm.handler;

import com.jankinwu.bkm.pojo.dto.RequestProcessContext;
import com.jankinwu.bkm.queue.DelayedTaskManager;
import com.jankinwu.bkm.queue.ScheduledQueueExecutor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.RejectedExecutionException;

/**
 * @author wwg
 * @description 延时队列处理
 * @date 2024/3/3 22:33
 */
@Service
@Slf4j
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
        try {
            if (!scheduledQueueExecutor.addToQueue(task)) {
                log.warn("用户[{}]{}的任务插入队列失败", context.getRequest().getData().getUname(), context.getProcess().getProcessName());
            } else {
                log.info("用户[{}]{}的任务插入队列成功", context.getRequest().getData().getUname(), context.getProcess().getProcessName());
            }
        } catch (RejectedExecutionException e) {
            log.warn("用户[{}]{}的任务被抛弃", context.getRequest().getData().getUname(), context.getProcess().getProcessName());
        }
        // 插入延迟队列
//        long delayTime = basicConfig.getDelayTime();
//        DelayedTask delayedTask = new DelayedTask(delayTime, task);
//        delayedTaskManager.addDelayedTask(delayedTask);
    }
}
