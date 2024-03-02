package com.jankinwu.bkm.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.awt.event.KeyEvent;

/**
 * @author jankin wu
 * @description 键位映射枚举类
 * @date 2024/2/29 22:05
 */
@Getter
@AllArgsConstructor
public enum KeyMappingAwtEnum {

    ESCAPE("ESCAPE", KeyEvent.VK_ESCAPE),
    F1("F1", KeyEvent.VK_F1),
    F2("F2", KeyEvent.VK_F2),
    F3("F3", KeyEvent.VK_F3),
    F4("F4", KeyEvent.VK_F4),
    F5("F5", KeyEvent.VK_F5),
    F6("F6", KeyEvent.VK_F6),
    F7("F7", KeyEvent.VK_F7),
    F8("F8", KeyEvent.VK_F8),
    F9("F9", KeyEvent.VK_F9),
    F10("F10", KeyEvent.VK_F10),
    F11("F11", KeyEvent.VK_F11),
    F12("F12", KeyEvent.VK_F12),
    PRINT_SCREEN("PRINT_SCREEN", KeyEvent.VK_PRINTSCREEN),
    SCROLL_LOCK("SCROLL_LOCK", KeyEvent.VK_SCROLL_LOCK),
    PAUSE("PAUSE", KeyEvent.VK_PAUSE),
    INSERT("INSERT", KeyEvent.VK_INSERT),
    HOME("HOME", KeyEvent.VK_HOME),
    PAGE_UP("PAGE_UP", KeyEvent.VK_PAGE_UP),
    DELETE("DELETE", KeyEvent.VK_DELETE),
    END("END", KeyEvent.VK_END),
    PAGE_DOWN("PAGE_DOWN", KeyEvent.VK_PAGE_DOWN),
    NUM_LOCK("NUM_LOCK", KeyEvent.VK_NUM_LOCK),
    CAPS_LOCK("CAPS_LOCK", KeyEvent.VK_CAPS_LOCK),
    SPACE("SPACE", KeyEvent.VK_SPACE),
    TAB("TAB", KeyEvent.VK_TAB),
    ENTER("ENTER", KeyEvent.VK_ENTER),
    BACKSPACE("BACKSPACE", KeyEvent.VK_BACK_SPACE),
    SHIFT("SHIFT", KeyEvent.VK_SHIFT),
    CONTROL("CONTROL", KeyEvent.VK_CONTROL),
    ALT("ALT", KeyEvent.VK_ALT),
    WINDOWS("WINDOWS", KeyEvent.VK_WINDOWS),
    CONTEXT_MENU("CONTEXT_MENU", KeyEvent.VK_CONTEXT_MENU),
    LEFT_WINDOWS("LEFT_WINDOWS", KeyEvent.VK_WINDOWS),
    // 左Alt键
    LEFT_ALT("LEFT_ALT", KeyEvent.VK_ALT_GRAPH),
    // 左Ctrl键
    LEFT_CONTROL("LEFT_CONTROL", KeyEvent.VK_CONTROL),
    // 左Shift键
    LEFT_SHIFT("LEFT_SHIFT", KeyEvent.VK_SHIFT),
    UP("UP", KeyEvent.VK_UP),
    DOWN("DOWN", KeyEvent.VK_DOWN),
    LEFT("LEFT", KeyEvent.VK_LEFT),
    RIGHT("RIGHT", KeyEvent.VK_RIGHT),
    NUMPAD_0("NUMPAD_0", KeyEvent.VK_NUMPAD0),
    NUMPAD_1("NUMPAD_1", KeyEvent.VK_NUMPAD1),
    NUMPAD_2("NUMPAD_2", KeyEvent.VK_NUMPAD2),
    NUMPAD_3("NUMPAD_3", KeyEvent.VK_NUMPAD3),
    NUMPAD_4("NUMPAD_4", KeyEvent.VK_NUMPAD4),
    NUMPAD_5("NUMPAD_5", KeyEvent.VK_NUMPAD5),
    NUMPAD_6("NUMPAD_6", KeyEvent.VK_NUMPAD6),
    NUMPAD_7("NUMPAD_7", KeyEvent.VK_NUMPAD7),
    NUMPAD_8("NUMPAD_8", KeyEvent.VK_NUMPAD8),
    NUMPAD_9("NUMPAD_9", KeyEvent.VK_NUMPAD9),
    NUMPAD_MULTIPLY("NUMPAD_MULTIPLY", KeyEvent.VK_MULTIPLY),
    NUMPAD_SUBTRACT("NUMPAD_SUBTRACT", KeyEvent.VK_SUBTRACT),
    NUMPAD_ADD("NUMPAD_ADD", KeyEvent.VK_ADD),
    NUMPAD_DECIMAL("NUMPAD_DECIMAL", KeyEvent.VK_DECIMAL),
    NUMPAD_DIVIDE("NUMPAD_DIVIDE", KeyEvent.VK_DIVIDE),
    NUMPAD_ENTER("NUMPAD_ENTER", KeyEvent.VK_ENTER),
    A("A", KeyEvent.VK_A),
    B("B", KeyEvent.VK_B),
    C("C", KeyEvent.VK_C),
    D("D", KeyEvent.VK_D),
    E("E", KeyEvent.VK_E),
    F("F", KeyEvent.VK_F),
    G("G", KeyEvent.VK_G),
    H("H", KeyEvent.VK_H),
    I("I", KeyEvent.VK_I),
    J("J", KeyEvent.VK_J),
    K("K", KeyEvent.VK_K),
    L("L", KeyEvent.VK_L),
    M("M", KeyEvent.VK_M),
    N("N", KeyEvent.VK_N),
    O("O", KeyEvent.VK_O),
    P("P", KeyEvent.VK_P),
    Q("Q", KeyEvent.VK_Q),
    R("R", KeyEvent.VK_R),
    S("S", KeyEvent.VK_S),
    T("T", KeyEvent.VK_T),
    U("U", KeyEvent.VK_U),
    V("V", KeyEvent.VK_V),
    W("W", KeyEvent.VK_W),
    X("X", KeyEvent.VK_X),
    Y("Y", KeyEvent.VK_Y),
    Z("Z", KeyEvent.VK_Z),
    DIGIT_0("DIGIT_0", KeyEvent.VK_0),
    DIGIT_1("DIGIT_1", KeyEvent.VK_1),
    DIGIT_2("DIGIT_2", KeyEvent.VK_2),
    DIGIT_3("DIGIT_3", KeyEvent.VK_3),
    DIGIT_4("DIGIT_4", KeyEvent.VK_4),
    DIGIT_5("DIGIT_5", KeyEvent.VK_5),
    DIGIT_6("DIGIT_6", KeyEvent.VK_6),
    DIGIT_7("DIGIT_7", KeyEvent.VK_7),
    DIGIT_8("DIGIT_8", KeyEvent.VK_8),
    DIGIT_9("DIGIT_9", KeyEvent.VK_9),
    MINUS("MINUS", KeyEvent.VK_MINUS),
    EQUALS("EQUALS", KeyEvent.VK_EQUALS),
    OPEN_BRACKET("OPEN_BRACKET", KeyEvent.VK_OPEN_BRACKET),
    CLOSE_BRACKET("CLOSE_BRACKET", KeyEvent.VK_CLOSE_BRACKET),
    BACKSLASH("BACKSLASH", KeyEvent.VK_BACK_SLASH),
    SEMICOLON("SEMICOLON", KeyEvent.VK_SEMICOLON),
    QUOTE("QUOTE", KeyEvent.VK_QUOTE),
    COMMA("COMMA", KeyEvent.VK_COMMA),
    PERIOD("PERIOD", KeyEvent.VK_PERIOD),
    SLASH("SLASH", KeyEvent.VK_SLASH),
    ;

    private final String keyName;
    private final int eventCode;

    public static Integer getEventCode(String keyName) {
        for (KeyMappingAwtEnum keyMappingAwtEnum : KeyMappingAwtEnum.values()) {
            if (keyMappingAwtEnum.getKeyName().equals(keyName)) {
                return keyMappingAwtEnum.getEventCode();
            }
        }
        return null;
    }
}
