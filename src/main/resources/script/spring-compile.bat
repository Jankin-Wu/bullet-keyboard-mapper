@echo off
setlocal

REM 项目根目录
set "projectRoot=%~dp0\..\..\..\.."

REM 指定使用pom-spring.xml文件打包
cd %projectRoot%
mvn -f pom-spring.xml clean package -DskipTests

echo compile successfully.

endlocal

