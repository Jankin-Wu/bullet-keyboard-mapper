# Bullet Keyboard Mapper

## 简介

+ 弹幕-键盘映射器，基于 Spring Native 开发，使用 WebSocket 获取哔哩哔哩直播弹幕映射键位并通过 JNA 模拟为键盘输入指令，支持编译为原生可执行文件，通过简单的配置即可用于开发弹幕互动游戏
+ `native-release-v1.0.0` 分支为使用 maven + GraalVM native image 打包的版本，之后的版本使用 Spring Boot Native 进行重构，简化打包流程

![image-20240305010917887](http://oss.jankinwu.com/img/image-20240305010917887.png)
<p style="text-align: center;">运行效果</p>

## 特性

### 本地可执行文件
基于 Spring Boot Native 技术开发，支持编译为原生可执行文件，不依赖 Java 运行环境即可运行。

### 支持多对一配置按键映射规则
弹幕和执行计划多对一映射

### 支持按键执行计划
把每一次操作指令（键盘 or 鼠标）设置为一个`stage`（阶段）, 每个`stage`里可定制组合键、按压时长、按压次数、前/后间隔时间和每次重复执行中的间隔时间，连续的相同按键指令可通过设置`repeatTimes`（重复次数）属性放进一个`stage`里，若干个`stage`组合起来即为一个`Process`（执行流程）。

### 支持定时任务队列
可根据实际需求配值任务队列大小和执行间隔

### 配置文件
+ `application-release.yml`：账户信息和功能配置文件，其中除身份码以外的账户信息需要注册哔哩哔哩开放平台获取，身份码在直播姬中即可获取。
+ `keyMapping.json`：弹幕内容和执行计划的映射关系，其中`msg`为弹幕内容，`processName`为对应执行计划名称。
+ `process 目录下的文件`: 执行计划文件，可配置按键执行流程，默认已配置好所有单键的执行计划。
## 使用说明
**release-v1.0.0 版**

解压`Bullet_Keyboard_Mapper.zip`后配置`config.yml`即可开始使用，修改`config.yml`或`keyMapping.json`文件需要重启应用后才能生效

**release-v1.0.0 之后版本**

解压`Bullet_Keyboard_Mapper.zip`后配置`application-release.yml`即可开始使用，修改`config`目录下的文件需要重启应用后才能生效

## 打包方式

### Jar 包
配置`config`目录下的`application-release.yml`文件后使用 Maven 打包：

```shell
man clean package
```
打包成功后运行 jar 包：
``` shell
 java -jar bullet-keyboard-mapper-1.0.jar
```
### Native 包
**打包前准备**

电脑里需要安装 `Microsoft Visual Studio 2022`以及 `GraalVM JDK17`并配置好环境变量，
具体可以参照这篇博客：https://huaweicloud.csdn.net/638754f0dacf622b8df8b0cc.html

运行以下命令安装 `native-image`
```shell
gu install native-image
```

**打包**

管理员权限运行 `x64 Native Tools Command Prompt for VS 2022` cd 到项目目录后执行：

```shell
mvn native:compile -Pnative -Prelease
```
由于打 native 包的时候会启动项目获取元数据（代理、配置文件、反射信息等），打包前请先配置好 `application-release.yml`中的各项参数，否则打包将会失败

## 计划功能
- [x] 支持组合键
- [x] 支持长按操作
- [x] 支持按键执行计划
- [ ] 支持鼠标指令
- [ ] 支持更多平台
- [ ] 支持弹幕消息限流
- [ ] 可视化交互界面（待定）
- [ ] 支持获取礼物信息
  
  More...
