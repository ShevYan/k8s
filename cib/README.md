# 介绍

每个目录是一个spring cloud服务，编译步骤：

* 安装jdk8
* 安装maven3
* 配置maven3的socks5代理，加快下载速度
```
export MAVEN_OPTS=-DsocksProxyHost=127.0.0.1 -DsocksProxyPort=1405
```
* 进入目录，执行
```
mvn clean package
```
* 编译Docker镜像
