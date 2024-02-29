package dialog;

import javax.swing.*;

/**
 * @author wwg
 * @description 异常弹窗
 * @date 2024/2/29 15:05
 */
public class ErrorDialog {
    public static void displayErrorMessage(String message) {
        JFrame frame = new JFrame("Error");
        JOptionPane.showMessageDialog(frame, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
