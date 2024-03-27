package com.jankinwu.bkm.service.factory;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSONObject;
import com.jankinwu.bkm.enums.LiveMsgTypeEnum;
import com.jankinwu.bkm.pojo.dto.RequestProcessContext;
import com.jankinwu.bkm.service.LiveMsgService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author jankinwu
 * @description b站直播消息处理工厂类
 * @date 2024/3/25 23:23
 */
@Component
public class LiveMsgServiceFactory implements ApplicationContextAware {

    /**
     * 存放对应的类型和实现类
     */
    private final Map<String, LiveMsgService> serviceMap = new ConcurrentHashMap<>();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, LiveMsgService> serviceMap = applicationContext.getBeansOfType(LiveMsgService.class);
        serviceMap.values().forEach(liveMsgService -> this.serviceMap.put(liveMsgService.getMsgType(), liveMsgService));
    }

    /**
     * 处理直播消息，根据 cmd 匹配对应的实现类
     * @param content 消息内容
     */
    public void handle(String content) {
        JSONObject contentJson = JSONObject.parseObject(content);
        String cmd = contentJson.getString("cmd");
        String type = LiveMsgTypeEnum.getTypeByCmd(cmd);
        if (StrUtil.isNotBlank(type)) {
            RequestProcessContext context = new RequestProcessContext(type);
            LiveMsgService liveMsgService = serviceMap.get(type);
            if (Objects.nonNull(liveMsgService)) {
                liveMsgService.handle(content, context);
            }
        }
    }
}
