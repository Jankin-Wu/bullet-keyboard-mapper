package com.jankinwu.bkm.config;

import com.jankinwu.bkm.handler.CustomRejectedExecutionHandler;
import com.jankinwu.bkm.hints.BulletHandlerRuntimeHints;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;

/**
 * @author jankinwu
 * @description bean 注册相关配置
 * @date 2024/3/2 1:25
 */
@Configuration
public class BeanConfig {

    @Bean
    public BulletHandlerRuntimeHints hintsRegistrar() {
        return new BulletHandlerRuntimeHints();
    }

    @Value("${app.scheduled.queue-capacity:100}")
    private int queueCapacity;

    @Bean("taskQueue")
    public BlockingQueue<Runnable> taskQueue() {
        return new LinkedBlockingQueue<>(queueCapacity);
    }

    @Bean("customRejectionPolicy")
    public RejectedExecutionHandler customRejectionPolicy() {
        return new CustomRejectedExecutionHandler();
    }

    /**
     * 这个bean的注册,用于扫描带有@ServerEndpoint的注解成为websocket,如果你使用外置的tomcat就不需要该配置文件
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

}
