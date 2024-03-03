package com.jankinwu.bkm;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.jankinwu.bkm.pojo.domain.Stage;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * @author jankinwu
 * @description
 * @date 2024/3/3 15:12
 */
//@SpringBootTest
public class JsonTest {

    @Test
    void covert() {
        String jsonString = "[\n" +
                "  {\"msg\": \"ESC\", \"key\": \"ESCAPE\"},\n" +
                "  {\"msg\": \"F1\", \"key\": \"F1\"},\n" +
                "  {\"msg\": \"F2\", \"key\": \"F2\"},\n" +
                "  {\"msg\": \"F3\", \"key\": \"F3\"},\n" +
                "  {\"msg\": \"F4\", \"key\": \"F4\"},\n" +
                "  {\"msg\": \"F5\", \"key\": \"F5\"},\n" +
                "  {\"msg\": \"F6\", \"key\": \"F6\"},\n" +
                "  {\"msg\": \"F7\", \"key\": \"F7\"},\n" +
                "  {\"msg\": \"F8\", \"key\": \"F8\"},\n" +
                "  {\"msg\": \"F9\", \"key\": \"F9\"},\n" +
                "  {\"msg\": \"F10\", \"key\": \"F10\"},\n" +
                "  {\"msg\": \"F11\", \"key\": \"F11\"},\n" +
                "  {\"msg\": \"F12\", \"key\": \"F12\"},\n" +
                "  {\"msg\": \"A\", \"key\": \"A\"},\n" +
                "  {\"msg\": \"B\", \"key\": \"B\"},\n" +
                "  {\"msg\": \"C\", \"key\": \"C\"},\n" +
                "  {\"msg\": \"D\", \"key\": \"D\"},\n" +
                "  {\"msg\": \"E\", \"key\": \"E\"},\n" +
                "  {\"msg\": \"F\", \"key\": \"F\"},\n" +
                "  {\"msg\": \"G\", \"key\": \"G\"},\n" +
                "  {\"msg\": \"H\", \"key\": \"H\"},\n" +
                "  {\"msg\": \"I\", \"key\": \"I\"},\n" +
                "  {\"msg\": \"J\", \"key\": \"J\"},\n" +
                "  {\"msg\": \"K\", \"key\": \"K\"},\n" +
                "  {\"msg\": \"L\", \"key\": \"L\"},\n" +
                "  {\"msg\": \"M\", \"key\": \"M\"},\n" +
                "  {\"msg\": \"N\", \"key\": \"N\"},\n" +
                "  {\"msg\": \"O\", \"key\": \"O\"},\n" +
                "  {\"msg\": \"P\", \"key\": \"P\"},\n" +
                "  {\"msg\": \"Q\", \"key\": \"Q\"},\n" +
                "  {\"msg\": \"R\", \"key\": \"R\"},\n" +
                "  {\"msg\": \"S\", \"key\": \"S\"},\n" +
                "  {\"msg\": \"T\", \"key\": \"T\"},\n" +
                "  {\"msg\": \"U\", \"key\": \"U\"},\n" +
                "  {\"msg\": \"V\", \"key\": \"V\"},\n" +
                "  {\"msg\": \"W\", \"key\": \"W\"},\n" +
                "  {\"msg\": \"X\", \"key\": \"X\"},\n" +
                "  {\"msg\": \"Y\", \"key\": \"Y\"},\n" +
                "  {\"msg\": \"Z\", \"key\": \"Z\"},\n" +
                "  {\"msg\": \"0\", \"key\": \"DIGIT_0\"},\n" +
                "  {\"msg\": \"1\", \"key\": \"DIGIT_1\"},\n" +
                "  {\"msg\": \"2\", \"key\": \"DIGIT_2\"},\n" +
                "  {\"msg\": \"3\", \"key\": \"DIGIT_3\"},\n" +
                "  {\"msg\": \"4\", \"key\": \"DIGIT_4\"},\n" +
                "  {\"msg\": \"5\", \"key\": \"DIGIT_5\"},\n" +
                "  {\"msg\": \"6\", \"key\": \"DIGIT_6\"},\n" +
                "  {\"msg\": \"7\", \"key\": \"DIGIT_7\"},\n" +
                "  {\"msg\": \"8\", \"key\": \"DIGIT_8\"},\n" +
                "  {\"msg\": \"9\", \"key\": \"DIGIT_9\"},\n" +
                "  {\"msg\": \"-\", \"key\": \"MINUS\"},\n" +
                "  {\"msg\": \"=\", \"key\": \"EQUALS\"},\n" +
                "  {\"msg\": \"[\", \"key\": \"OPEN_BRACKET\"},\n" +
                "  {\"msg\": \"]\", \"key\": \"CLOSE_BRACKET\"},\n" +
                "  {\"msg\": \"\\\\\", \"key\": \"BACKSLASH\"},\n" +
                "  {\"msg\": \";\", \"key\": \"SEMICOLON\"},\n" +
                "  {\"msg\": \"'\", \"key\": \"QUOTE\"},\n" +
                "  {\"msg\": \",\", \"key\": \"COMMA\"},\n" +
                "  {\"msg\": \".\", \"key\": \"PERIOD\"},\n" +
                "  {\"msg\": \"/\", \"key\": \"SLASH\"},\n" +
                "  {\"msg\": \"SPACE\", \"key\": \"SPACE\"},\n" +
                "  {\"msg\": \"TAB\", \"key\": \"TAB\"},\n" +
                "  {\"msg\": \"ENTER\", \"key\": \"ENTER\"},\n" +
                "  {\"msg\": \"测试ENTER\", \"key\": \"ENTER\"},\n" +
                "  {\"msg\": \"BACKSPACE\", \"key\": \"BACKSPACE\"},\n" +
                "  {\"msg\": \"SHIFT\", \"key\": \"SHIFT\"},\n" +
                "  {\"msg\": \"CONTROL\", \"key\": \"CONTROL\"},\n" +
                "  {\"msg\": \"ALT\", \"key\": \"ALT\"},\n" +
                "  {\"msg\": \"WINDOWS\", \"key\": \"WINDOWS\"},\n" +
                "  {\"msg\": \"CONTEXT_MENU\", \"key\": \"CONTEXT_MENU\"},\n" +
                "  {\"msg\": \"LEFT_WINDOWS\", \"key\": \"LEFT_WINDOWS\"},\n" +
                "  {\"msg\": \"RIGHT_WINDOWS\", \"key\": \"RIGHT_WINDOWS\"},\n" +
                "  {\"msg\": \"LEFT_ALT\", \"key\": \"LEFT_ALT\"},\n" +
                "  {\"msg\": \"LEFT_CONTROL\", \"key\": \"LEFT_CONTROL\"},\n" +
                "  {\"msg\": \"LEFT_SHIFT\", \"key\": \"LEFT_SHIFT\"},\n" +
                "  {\"msg\": \"UP\", \"key\": \"UP\"},\n" +
                "  {\"msg\": \"DOWN\", \"key\": \"DOWN\"},\n" +
                "  {\"msg\": \"LEFT\", \"key\": \"LEFT\"},\n" +
                "  {\"msg\": \"RIGHT\", \"key\": \"RIGHT\"},\n" +
                "  {\"msg\": \"NUMPAD_0\", \"key\": \"NUMPAD_0\"},\n" +
                "  {\"msg\": \"NUMPAD_1\", \"key\": \"NUMPAD_1\"},\n" +
                "  {\"msg\": \"NUMPAD_2\", \"key\": \"NUMPAD_2\"},\n" +
                "  {\"msg\": \"NUMPAD_3\", \"key\": \"NUMPAD_3\"},\n" +
                "  {\"msg\": \"NUMPAD_4\", \"key\": \"NUMPAD_4\"},\n" +
                "  {\"msg\": \"NUMPAD_5\", \"key\": \"NUMPAD_5\"},\n" +
                "  {\"msg\": \"NUMPAD_6\", \"key\": \"NUMPAD_6\"},\n" +
                "  {\"msg\": \"NUMPAD_7\", \"key\": \"NUMPAD_7\"},\n" +
                "  {\"msg\": \"NUMPAD_8\", \"key\": \"NUMPAD_8\"},\n" +
                "  {\"msg\": \"NUMPAD_9\", \"key\": \"NUMPAD_9\"},\n" +
                "  {\"msg\": \"NUMPAD_MULTIPLY\", \"key\": \"NUMPAD_MULTIPLY\"},\n" +
                "  {\"msg\": \"NUMPAD_SUBTRACT\", \"key\": \"NUMPAD_SUBTRACT\"},\n" +
                "  {\"msg\": \"NUMPAD_ADD\", \"key\": \"NUMPAD_ADD\"},\n" +
                "  {\"msg\": \"NUMPAD_DECIMAL\", \"key\": \"NUMPAD_DECIMAL\"},\n" +
                "  {\"msg\": \"NUMPAD_DIVIDE\", \"key\": \"NUMPAD_DIVIDE\"},\n" +
                "  {\"msg\": \"NUMPAD_ENTER\", \"key\": \"NUMPAD_ENTER\"},\n" +
                "  {\"msg\": \"INSERT\", \"key\": \"INSERT\"},\n" +
                "  {\"msg\": \"DELETE\", \"key\": \"DELETE\"},\n" +
                "  {\"msg\": \"HOME\", \"key\": \"HOME\"},\n" +
                "  {\"msg\": \"END\", \"key\": \"END\"},\n" +
                "  {\"msg\": \"PAGE_UP\", \"key\": \"PAGE_UP\"},\n" +
                "  {\"msg\": \"PAGE_DOWN\", \"key\": \"PAGE_DOWN\"},\n" +
                "  {\"msg\": \"CAPS_LOCK\", \"key\": \"CAPS_LOCK\"},\n" +
                "  {\"msg\": \"SCROLL_LOCK\", \"key\": \"SCROLL_LOCK\"},\n" +
                "  {\"msg\": \"PAUSE\", \"key\": \"PAUSE\"},\n" +
                "  {\"msg\": \"PRINT_SCREEN\", \"key\": \"PRINT_SCREEN\"}\n" +
                "]";

                List<Object> original = JSON.parseArray(jsonString);
        List<Map<String, Object>> result = new ArrayList<>();

        for (Object item : original) {
            JSONObject obj = (JSONObject) item;

            Map<String, Object> procesee = new HashMap<>();
            procesee.put("processName", obj.get("key"));

            Stage stage = new Stage();
            stage.setKeys(Collections.singletonList((String) obj.get("key")));
            stage.setName(obj.getString("key"));

            procesee.put("stages", Collections.singletonList(stage));

            result.add(procesee);
        }

        String resultJsonString = JSON.toJSONString(result);
        System.out.println(resultJsonString);
    }
}
