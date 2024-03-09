package com.jankinwu.bkm.utils;

import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;

import java.util.Map;

/**
 * @author jankinwu
 * @description 字符串工具类
 * @date 2024/3/8 16:43
 */
public class StringUtils extends StrUtil {

    public static String replacePlaceholders(String input, Map<String, String> parameters) {
        input = input.replace("\\", "");
        for (Map.Entry<String, String> entry : parameters.entrySet()) {
            String placeholder = "\\$\\{" + entry.getKey() + "\\}";
            String value = entry.getValue();
            input = ReUtil.replaceAll(input, placeholder, value);
        }

        return input;
    }
}
