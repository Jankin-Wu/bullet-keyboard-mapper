import config.BasicConfig;
import config.GlobalConfigHolder;
import dialog.ErrorDialog;
import handler.ConfigHandler;
import service.BulletServerService;
import utils.ConfigUtils;

/**
 * @author jankinwu
 * @description 启动器
 * @date 2024/2/25 13:09
 */
public class Starter {

    public static BasicConfig basicConfig;
    public static void main(String[] args) {
        try {
            init();
            BulletServerService.requestServer(basicConfig);
        } catch (Exception e) {
            ErrorDialog.displayErrorMessage(e.getMessage());
            // 在点击对话框确定按钮后退出应用程序
            System.exit(1);
        }
    }

    private static void init() {
        System.setProperty("java.home", ".");
        // 读取配置文件
        basicConfig = GlobalConfigHolder.getBasicConfig();
//        basicConfig = ConfigUtils.getConfig("./config-dev.yml", BasicConfig.class);
        // 校验配置参数
        ConfigHandler.verifyConfig(basicConfig);
//        Log4jConfig.configureConsoleAppender();
    }

}
