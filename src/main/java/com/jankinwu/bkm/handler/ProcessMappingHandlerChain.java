package com.jankinwu.bkm.handler;

import cn.hutool.core.util.StrUtil;
import com.jankinwu.bkm.cache.KeyMappingCache;
import com.jankinwu.bkm.cache.ProcessCache;
import com.jankinwu.bkm.config.AppConfig;
import com.jankinwu.bkm.hints.BulletHandlerRuntimeHints;
import com.jankinwu.bkm.hints.KeyboardSimuRuntimeHints;
import com.jankinwu.bkm.pojo.domain.ProcessData;
import com.jankinwu.bkm.pojo.dto.RequestProcessContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ImportRuntimeHints;
import org.springframework.stereotype.Component;

import java.util.Objects;


/**
 * @author jankinwu
 * @description 弹幕处理器
 * @date 2024/2/26 23:34
 */
@Slf4j
@Component
@RequiredArgsConstructor
@ImportRuntimeHints({BulletHandlerRuntimeHints.class, KeyboardSimuRuntimeHints.class})
public class ProcessMappingHandlerChain extends AbstractBulletResponseHandlerChain {

    private final AppConfig appConfig;

    private final ProcessCache processCache;

    private final KeyMappingCache keyMappingCache;
//    public static final String FILE_NAME = "keyMapping.json";

    @Override
    public void doChain(RequestProcessContext context) {
        String msg = context.getCommonData().getMsg();
        if (Objects.isNull(processCache.getProcessMap())) {
            log.error("执行计划获取失败，请检查process文件中的格式是否正确");
            return;
        }
        // 根据是否忽略大小写匹配执行计划
        ProcessData processData = keyMappingCache.getKeyProcessMap().entrySet().stream()
                .filter(entry -> StrUtil.equals(entry.getKey(), msg, appConfig.getIgnoreCase()))
                .map(entry -> {
                    ProcessData data = processCache.getProcessMap().get(entry.getValue());
                    if (data == null) {
                        log.error("未匹配到对应的执行计划：[{}]", entry.getValue());
                    }
                    return data;
                })
                .filter(Objects::nonNull)
                .findFirst()
                .orElse(null);
        if (Objects.isNull(processData)) {
            return;
        }
        context.setProcess(processData);
        if (getNext() != null) {
            getNext().doChain(context);
        }
    }
}
