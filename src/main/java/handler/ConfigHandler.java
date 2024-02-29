package handler;

import cn.hutool.core.util.StrUtil;
import config.BasicConfig;

import java.util.Objects;

/**
 * @author wwg
 * @description 配置信息处理
 * @date 2024/2/29 15:15
 */
public class ConfigHandler {

    public static void verifyConfig(BasicConfig basicConfig) {
        if (StrUtil.isBlank(basicConfig.getAccessKey())) {
            throw new RuntimeException("access_key 不能为空");
        }

        if (StrUtil.isBlank(basicConfig.getAccessSecret())) {
            throw new RuntimeException("access_secret 不能为空");
        }

        if (Objects.isNull(basicConfig.getAppId())) {
            throw new RuntimeException("appId 不能为空");
        }

        if (StrUtil.isBlank(basicConfig.getIdCode())) {
            throw new RuntimeException("身份码不能为空");
        }
    }
}
