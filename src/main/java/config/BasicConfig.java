package config;

import lombok.Data;

/**
 * @author jankinwu
 * @description 基础配置
 * @date 2024/2/25 13:17
 */
@Data
public class BasicConfig {

    private String idCode;

    private String accessKey;

    private String accessSecret;

    private Long appId;
}
