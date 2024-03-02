# Bullet Keyboard Mapper

## 简介

+ 基于 WebSocket 获取哔哩哔哩直播弹幕映射键位并通过 JNA 模拟为键盘输入指令，可用于开发弹幕互动游戏
+ `native-release-v1.0.0` 分支为使用 maven + GraalVM native image 打包的版本，之后的版本使用 Spring Boot Native 进行重构，简化打包流程

<img alt="界面展示" src=".\src\main\resources\img\interface.png" title="界面展示"/>
<p style="text-align: center;">运行效果</p>

## 特性
### 支持多条弹幕内容映射一个按键

### 本地可执行文件
基于 Spring Boot Native 技术开发，支持编译为原生可执行文件，不依赖 Java 运行环境即可运行。

### 配置文件
+ `application-release.yml`：账户信息和功能配置文件
+ `keyMapping.json`：弹幕内容和键位的映射关系，其中`msg`为弹幕内容，`key`为触发键位。

## 使用说明
**release-v1.0.0 版**

解压`Bullet_Keyboard_Mapper.zip`后配置`config.yml`即可开始使用，修改`config.yml`或`keyMapping.json`文件需要重启应用后才能生效

**release-v1.0.0 之后版本**

解压`Bullet_Keyboard_Mapper.zip`后配置`application-release.yml`即可开始使用，修改`application-release.yml`或`keyMapping.json`文件需要重启应用后才能生效

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
- [ ] 支持组合键
- [ ] 支持长按操作
- [ ] 支持按键执行计划
- [ ] 支持鼠标指令
- [ ] 支持更多平台
- [ ] 支持弹幕消息限流
- [ ] 可视化交互界面（待定）
  
  More...
