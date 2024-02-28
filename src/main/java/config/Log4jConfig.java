package config;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

/**
 * @author wwg
 * @description
 * @date 2024/2/28 10:21
 */
public class Log4jConfig {
    public static void configureConsoleAppender() {
        ConsoleAppender consoleAppender = new ConsoleAppender();
        consoleAppender.setEncoding("UTF-8");

//        PatternLayout layout = new PatternLayout();
//        layout.setConversionPattern("%d [%t] %p %c - %m%n");
//        consoleAppender.setLayout(layout);

        Logger.getRootLogger().addAppender(consoleAppender);
    }
}
