import config.BasicConfig;
import service.BulletService;
import utils.ConfigUtils;

/**
 * @author jankinwu
 * @description 启动器
 * @date 2024/2/25 13:09
 */
public class Starter {

    public static BasicConfig basicConfig;
    public static void main(String[] args) {
        init();
        BulletService.requestServer(basicConfig);
    }

    private static void init() {
        // 读取配置文件
        basicConfig = ConfigUtils.getConfig("./config.yml", BasicConfig.class);
//        Log4jConfig.configureConsoleAppender();
    }

}
