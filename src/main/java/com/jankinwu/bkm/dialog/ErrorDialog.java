package com.jankinwu.bkm.dialog;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;


/**
 * @author jankinwu
 * @description 异常弹窗
 * @date 2024/2/29 15:05
 */
public class ErrorDialog extends Application {
//    public static void displayErrorMessage(String message) {
//        JFrame frame = new JFrame("Error");
//        JOptionPane.showMessageDialog(frame, message, "Error", JOptionPane.ERROR_MESSAGE);
//    }

    private void showErrorDialog(Throwable throwable) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("An error has occurred.");
        alert.setContentText(throwable.getMessage());
        alert.setOnHidden(event -> {
            // 点击确定按钮后关闭进程并返回状态码1
            System.exit(1);
        });
        alert.showAndWait();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("your_fxml_file.fxml"));

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("JavaFX Application");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
