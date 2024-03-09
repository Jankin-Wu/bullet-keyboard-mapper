package com.jankinwu.bkm.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author jankinwu
 * @description 通知消息配置
 * @date 2024/3/9 21:36
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "app.push.msg")
public class PushMsgConfig {

    private String text;
    private String fontSize;
    private String fill;
    private String stroke;
    private String fontFamily;
    private String type;
}
