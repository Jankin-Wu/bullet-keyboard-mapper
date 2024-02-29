package config;

import utils.ConfigUtils;

/**
 * @author wwg
 * @description 全局配置持有者
 * @date 2024/2/29 23:22
 */
public class GlobalConfigHolder {

    private static final BasicConfig BASIC_CONFIG = ConfigUtils.getConfig("./config.yml", BasicConfig.class);

    private GlobalConfigHolder() {

    }

    public static BasicConfig getBasicConfig() {
        return BASIC_CONFIG;
    }
}
