# Bullet Keyboard Mapper
基于 WebSocket 获取哔哩哔哩直播弹幕并映射为键盘指令，可用于开发弹幕互动游戏

## 特性
### 支持多条弹幕内容映射一个按键

### 本地可执行文件
`feature-native`分支基于 GraalVM Native Image 技术开发，支持编译为原生可执行文件，不依赖 Java 运行环境即可运行。

### 配置文件
+ `config.yml`：账户信息和功能配置文件
+ `keyMapping.json`：弹幕内容和键位的映射关系，其中`msg`为弹幕内容，`key`为触发键位。

## 使用方式

### Jar 版
配置`master`分支`src/resources`目录下的`config.yml`文件后使用 Maven 打包：

```shell
man clean package
```
打包成功后运行 jar 包：
``` shell
 java -jar bullet-keyboard-mapper-1.0.jar
```
### Native 版
解压`Bullet_Keyboard_Mapper.zip`后配置`config.yml`即可开始使用，修改`config.yml`或`keyMapping.json`文件需要重启应用后才能生效

## Native 分支打包步骤


## 计划功能
+ 支持组合键
+ 支持长按操作
+ 支持按键执行计划
+ 支持鼠标指令
+ 支持更多平台
+ 支持弹幕消息限流
+ 可视化交互界面（待定）
  
  More...
