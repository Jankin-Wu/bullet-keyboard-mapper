package com.jankinwu.bkm.handler;

import com.jankinwu.bkm.pojo.domain.Stage;
import com.jankinwu.bkm.pojo.dto.RequestProcessContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

/**
 * @author jankinwu
 * @description 执行计划处理器
 * @date 2024/3/3 17:32
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class ProcessHandleChain extends AbstractBulletResponseHandlerChain {

    private final KeyboardHandler keyboardHandler;

    private final MouseHandler mouseHandler;

    @Override
    public void doChain(RequestProcessContext context) {
        if (Objects.isNull(context.getProcess())) {
            return;
        }
        log.info("开始处理用户[{}]的任务[{}]", context.getBulletCommentRequest().getData().getUname(), context.getProcess().getProcessName());
        for (Stage stage : context.getProcess().getStages()) {
            try {
                executeStage(stage);
                log.info("stage[{}]执行完毕", stage.getName());
            } catch (InvocationTargetException e) {
                log.error("stage [{}] 处理异常：{}",stage.getName(), e.getTargetException().getMessage());
            } catch (NoSuchMethodException | IllegalAccessException e) {
                log.error("stage [{}] 处理异常：{}",stage.getName(), e.getMessage());
            }
        }
        log.info("用户[{}]的任务[{}]执行完毕", context.getBulletCommentRequest().getData().getUname(), context.getProcess().getProcessName());
        if (getNext() != null) {
            getNext().doChain(context);
        }
    }

    private void executeStage(Stage stage) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        log.info("Executing stage: " + stage.getName());
        try {
            Thread.sleep(stage.getIntervalBefore());
        } catch (InterruptedException e) {
            log.error("", e);
        }

        for (int i = 0; i < stage.getRepeatTimes(); i++) {
            if (stage.isMouse()) {
                mouseHandler.execute(stage);
            } else {
                keyboardHandler.execute(stage);
            }
            // 执行间隔
            if (i < stage.getRepeatTimes() - 1) {
                try {
                    Thread.sleep(stage.getRepeatInterval());
                } catch (InterruptedException e) {
                    log.error("", e);
                }
            }
        }
        try {
            Thread.sleep(stage.getIntervalAfter());
        } catch (InterruptedException e) {
            log.error("", e);
        }
    }
}
