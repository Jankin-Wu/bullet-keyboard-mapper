package com.jankinwu.bkm.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author jankinwu
 * @description Jna key code 映射
 * @date 2024/3/2 16:11
 */
@Getter
@AllArgsConstructor
public enum KeyMappingJnaEnum {

    ESCAPE((byte) 0x1B, "ESCAPE"),
    F1((byte) 0x70, "F1"),
    F2((byte) 0x71, "F2"),
    F3((byte) 0x72, "F3"),
    F4((byte) 0x73, "F4"),
    F5((byte) 0x74, "F5"),
    F6((byte) 0x75, "F6"),
    F7((byte) 0x76, "F7"),
    F8((byte) 0x77, "F8"),
    F9((byte) 0x78, "F9"),
    F10((byte) 0x79, "F10"),
    F11((byte) 0x7A, "F11"),
    F12((byte) 0x7B, "F12"),
    A((byte) 'A', "A"),
    B((byte) 'B', "B"),
    C((byte) 'C', "C"),
    D((byte) 'D', "D"),
    E((byte) 'E', "E"),
    F((byte) 'F', "F"),
    G((byte) 'G', "G"),
    H((byte) 'H', "H"),
    I((byte) 'I', "I"),
    J((byte) 'J', "J"),
    K((byte) 'K', "K"),
    L((byte) 'L', "L"),
    M((byte) 'M', "M"),
    N((byte) 'N', "N"),
    O((byte) 'O', "O"),
    P((byte) 'P', "P"),
    Q((byte) 'Q', "Q"),
    R((byte) 'R', "R"),
    S((byte) 'S', "S"),
    T((byte) 'T', "T"),
    U((byte) 'U', "U"),
    V((byte) 'V', "V"),
    W((byte) 'W', "W"),
    X((byte) 'X', "X"),
    Y((byte) 'Y', "Y"),
    Z((byte) 'Z', "Z"),
    DIGIT_0((byte) '0', "DIGIT_0"),
    DIGIT_1((byte) '1', "DIGIT_1"),
    DIGIT_2((byte) '2', "DIGIT_2"),
    DIGIT_3((byte) '3', "DIGIT_3"),
    DIGIT_4((byte) '4', "DIGIT_4"),
    DIGIT_5((byte) '5', "DIGIT_5"),
    DIGIT_6((byte) '6', "DIGIT_6"),
    DIGIT_7((byte) '7', "DIGIT_7"),
    DIGIT_8((byte) '8', "DIGIT_8"),
    DIGIT_9((byte) '9', "DIGIT_9"),
    MINUS((byte) '-', "MINUS"),
    EQUALS((byte) '=', "EQUALS"),
    OPEN_BRACKET((byte) '[', "OPEN_BRACKET"),
    CLOSE_BRACKET((byte) ']', "CLOSE_BRACKET"),
    BACKSLASH((byte) '\\', "BACKSLASH"),
    SEMICOLON((byte) ';', "SEMICOLON"),
    QUOTE((byte) '\'', "QUOTE"),
    COMMA((byte) ',', "COMMA"),
    PERIOD((byte) '.', "PERIOD"),
    SLASH((byte) '/', "SLASH"),
    SPACE((byte) 0x20, "SPACE"),
    TAB((byte) 0x09, "TAB"),
    ENTER((byte) 0x0D, "ENTER"),
    BACKSPACE((byte) 0x08, "BACKSPACE"),
    SHIFT((byte) 0x10, "SHIFT"),
    CONTROL((byte) 0x11, "CONTROL"),
    ALT((byte) 0x12, "ALT"),
    WINDOWS((byte) 0x5B, "WINDOWS"),
    CONTEXT_MENU((byte) 0x5D, "CONTEXT_MENU"),
    LEFT_WINDOWS((byte) 0x5B, "LEFT_WINDOWS"),
    RIGHT_WINDOWS((byte) 0x5C, "RIGHT_WINDOWS"),
    LEFT_ALT((byte) 0xA4, "LEFT_ALT"),
    LEFT_CONTROL((byte) 0xA2, "LEFT_CONTROL"),
    LEFT_SHIFT((byte) 0xA0, "LEFT_SHIFT"),
    UP((byte) 0x26, "UP"),
    DOWN((byte) 0x28, "DOWN"),
    LEFT((byte) 0x25, "LEFT"),
    RIGHT((byte) 0x27, "RIGHT"),
    NUMPAD_0((byte) 0x60, "NUMPAD_0"),
    NUMPAD_1((byte) 0x61, "NUMPAD_1"),
    NUMPAD_2((byte) 0x62, "NUMPAD_2"),
    NUMPAD_3((byte) 0x63, "NUMPAD_3"),
    NUMPAD_4((byte) 0x64, "NUMPAD_4"),
    NUMPAD_5((byte) 0x65, "NUMPAD_5"),
    NUMPAD_6((byte) 0x66, "NUMPAD_6"),
    NUMPAD_7((byte) 0x67, "NUMPAD_7"),
    NUMPAD_8((byte) 0x68, "NUMPAD_8"),
    NUMPAD_9((byte) 0x69, "NUMPAD_9"),
    NUMPAD_MULTIPLY((byte) 0x6A, "NUMPAD_MULTIPLY"),
    NUMPAD_SUBTRACT((byte) 0x6D, "NUMPAD_SUBTRACT"),
    NUMPAD_ADD((byte) 0x6B, "NUMPAD_ADD"),
    NUMPAD_DECIMAL((byte) 0x6E, "NUMPAD_DECIMAL"),
    NUMPAD_DIVIDE((byte) 0x6F, "NUMPAD_DIVIDE"),
    NUMPAD_ENTER((byte) 0x0D, "NUMPAD_ENTER"),
    INSERT((byte) 0x2D, "INSERT"),
    DELETE((byte) 0x2E, "DELETE"),
    HOME((byte) 0x24, "HOME"),
    END((byte) 0x23, "END"),
    PAGE_UP((byte) 0x21, "PAGE_UP"),
    PAGE_DOWN((byte) 0x22, "PAGE_DOWN"),
    CAPS_LOCK((byte) 0x14, "CAPS_LOCK"),
    SCROLL_LOCK((byte) 0x91, "SCROLL_LOCK"),
    PAUSE((byte) 0x13, "PAUSE"),
    PRINT_SCREEN((byte) 0x2C, "PRINT_SCREEN");

    private final byte keyCode;
    private final String keyName;

    public static Byte getKeyCode(String keyName) {
        for (KeyMappingJnaEnum keyMappingJnaEnum : KeyMappingJnaEnum.values()) {
            if (keyMappingJnaEnum.getKeyName().equals(keyName)) {
                return keyMappingJnaEnum.getKeyCode();
            }
        }
        return null;
    }
}
