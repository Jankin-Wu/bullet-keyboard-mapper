package com.jankinwu.bkm.handler;

import com.jankinwu.bkm.pojo.dto.RequestProcessContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author jankinwu
 * @description 限流处理
 * @date 2024/3/5 12:48
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class LimitRateHandlerChain extends AbstractBulletResponseHandlerChain {

    @Override
    public void doChain(RequestProcessContext context) {
        if (getNext() != null) {
            getNext().doChain(context);
        }
    }
}
