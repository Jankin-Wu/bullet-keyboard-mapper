package com.jankinwu.bkm.handler;

import cn.hutool.core.util.StrUtil;
import com.jankinwu.bkm.config.AppConfig;
import com.jankinwu.bkm.enums.KeySimMethodEnum;
import com.jankinwu.bkm.pojo.domain.Stage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @author jankinwu
 * @description 处理按键操作流程
 * @date 2024/3/3 3:46
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class KeyboardHandler {

    private final AppConfig appConfig;

    public void execute(Stage stage) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        // 按照顺序按下键
        for (String key : stage.getKeys()) {
            pressKey(key);
        }
        // 等待holdTime时长
        try {
            Thread.sleep(stage.getHoldTime());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 释放按键
        for (String key : stage.getKeys()) {
            releaseKey(key);
        }
        log.info("模拟按键：{}", String.join(" + ", stage.getKeys()));
    }

    private void pressKey(String key) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // 执行按下key的操作
        if (StrUtil.isNotBlank(key)) {
            // 获取相应模拟键盘工具类并触发按键
            Class<?> simUtils = KeySimMethodEnum.getSimUtils(appConfig.getSimMethod());
            if (Objects.nonNull(simUtils)) {
                Method method = simUtils.getMethod("press", String.class);
                method.invoke(null, key);
            }
        }
    }

    private void releaseKey(String key) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        // 执行释放key的操作
        if (StrUtil.isNotBlank(key)) {
            // 获取相应模拟键盘工具类并触发按键
            Class<?> simUtils = KeySimMethodEnum.getSimUtils(appConfig.getSimMethod());
            if (Objects.nonNull(simUtils)) {
                Method method = simUtils.getMethod("release", String.class);
                method.invoke(null, key);
            }
        }
    }
}
