package com.jankinwu.bkm.handler;

import com.jankinwu.bkm.pojo.domain.Coordinate;
import com.jankinwu.bkm.pojo.domain.Stage;
import com.jankinwu.bkm.utils.MouseSimulationJnaUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author jankinwu
 * @description 处理鼠标操作
 * @date 2024/3/3 18:52
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class MouseHandler {

    public void execute(Stage stage) {
        moveMouse(stage.getCoordinate());
        if(stage.isScroll()) {
            handlerScroll(stage);
            return;
        }
        // 按照顺序按下键
        for (String key : stage.getKeys()) {
            pressKey(key);
        }
        holdTime(stage.getHoldTime());
        // 释放按键
        for (String key : stage.getKeys()) {
            releaseKey(key);
        }
        log.info("模拟按键：{}", String.join(" + ", stage.getKeys()));

    }

    private void moveMouse(Coordinate coordinate) {
        if (Objects.nonNull(coordinate)) {
            MouseSimulationJnaUtils.moveMouseToCoordinate(coordinate.getX(), coordinate.getY());
        }
    }

    private void holdTime(int holdTime) {
        // 等待holdTime时长
        try {
            Thread.sleep(holdTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void releaseKey(String key) {
        MouseSimulationJnaUtils.release(key);
    }

    private void pressKey(String key) {
        MouseSimulationJnaUtils.press(key);
    }

    private void handlerScroll(Stage stage) {
        log.info("模拟滚轮，滚动量：{}", stage.getScrollAmount());
    }
}
