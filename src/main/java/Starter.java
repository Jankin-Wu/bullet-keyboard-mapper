import service.BulletService;

/**
 * @author jankinwu
 * @description 启动器
 * @date 2024/2/25 13:09
 */
public class Starter {
    public static void main(String[] args) {
        init();
        BulletService.requestServer();
    }

    private static void init() {
//        Log4jConfig.configureConsoleAppender();
    }

}
