package com.jankinwu.bkm.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author jankinwu
 * @description 基础配置
 * @date 2024/2/25 13:17
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "app")
public class AppConfig {

    private String idCode;

    private String accessKey;

    private String accessSecret;

    private Long appId;

    private Boolean ignoreCase = true;

    /**
     * 触发方式（awt or jna），由于 GraalVM native image 打包原生镜像时不支持awt，所以默认使用 jna 实现按键模拟
     */
    private String simMethod = "jna";

    private String mappingFilePath;

    private String processDir;

    private Integer delayTime = 1000;

    private String executionFormatString;

    private List<String> processesDontPush;
}
