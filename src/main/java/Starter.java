import service.BulletService;

import java.io.IOException;

/**
 * @author jankinwu
 * @description 启动器
 * @date 2024/2/25 13:09
 */
public class Starter {
    public static void main(String[] args) {
        init();
        BulletService service = new BulletService();
        service.requestServer();
    }

    private static void init() {
        // 执行前修改终端输出日志编码
//        ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", "chcp 65001");
        // 将子进程的输入/输出与当前进程绑定
//        processBuilder.inheritIO();
//        try {
//            processBuilder.start();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        Log4jConfig.configureConsoleAppender();
    }

}
