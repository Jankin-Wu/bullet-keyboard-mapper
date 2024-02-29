# BulletKeyboardMapper
基于 WebSocket 获取哔哩哔哩直播弹幕并映射为键盘指令

## 特性

### 本地可执行文件
`feature-native`分支基于 GraalVM Native Image 技术开发，支持编译为原生可执行文件，不依赖 Java 运行环境即可运行。

## 使用方式
### Jar 版
``` shell
 java -jar bullet-keyboard-mapper-1.0.jar
```
### Native 版
解压后配置`config.yml`即可开始使用，修改`config.yml`或`keyMapping.json`文件需要重启应用后才能生效
