package handler;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson2.JSONObject;
import utils.KeyboardSimulationUtils;

/**
 * @author jankinwu
 * @description 弹幕处理器
 * @date 2024/2/26 23:34
 */
public class DanMuHandler {

    public void handleDanMu(String content) {
        JSONObject contentJson = JSONObject.parseObject(content);
        JSONObject data = contentJson.getJSONObject("data");
        String msg = data.getString("msg");
        String uname = data.getString("uname");
        System.out.println(DateUtil.now() + " [弹幕] " + uname + "：" + msg);
        if ("1".equals(msg)) {
            KeyboardSimulationUtils.pressAndRelease('1');
        } else if ("2".equals(msg)) {
            KeyboardSimulationUtils.pressAndRelease('2');
        }
    }
}
