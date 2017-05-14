# 无障碍上网

## 原理

Socks和Http都是代理协议，都只是针对浏览器和http访问的，跟vpn有本质的差异，vpn是从2-3层打通网络，所以是能够ping通的，而socks和http不会影响ping。因此，一般软件都需要设置代理，通常是http_proxy和https_proxy环境变量。比如git就可以配置代理。

##  第一步，选定机房和线路
---
### 地域
---
日本：价格较美国贵，机房选择少，用户多，不稳定。

香港：价格贵，带宽低，路由不稳定。

新加坡：价格贵，路由不稳定。

美国：价格便宜，机房选择多。

推荐 美国选择西海岸：San Francisco, Los Angeles

### 线路
---

中国电信通往美国有两个线路：

- CN2: 新线路，质量较高，主要用于高质量需求的大客户，机房资源少；
- ChinaNet: 老线路，大众使用，高峰时期丢包严重；

中国联通和移动相对来说用户较少，线路质量高于电信，不需要过多关注线路。

### 参考数据
---
ping在 220ms 下比较正常
```
PING ns.xcai.net (192.157.192.2): 56 data bytes
64 bytes from 192.157.192.2: icmp_seq=0 ttl=53 time=183.732 ms
64 bytes from 192.157.192.2: icmp_seq=1 ttl=53 time=182.749 ms
64 bytes from 192.157.192.2: icmp_seq=2 ttl=53 time=184.632 ms
64 bytes from 192.157.192.2: icmp_seq=3 ttl=53 time=184.149 ms
64 bytes from 192.157.192.2: icmp_seq=4 ttl=53 time=183.329 ms
```

traceroute 结果
```
shing@ubuntu:~$ traceroute ns.xcai.net
traceroute to ns.xcai.net (192.157.192.2), 30 hops max, 60 byte packets
1  Gargoyle.lan (192.168.1.1)  3.305 ms  3.256 ms  3.249 ms
2  1.20.88.218.broad.cd.sc.dynamic.163data.com.cn (218.88.20.1)  16.272 ms  16.260 ms  16.255 ms
3  * * *
4  171.208.199.101 (171.208.199.101)  8.372 ms 171.208.203.57 (171.208.203.57)  10.824 ms 171.208.203.69 (171.208.203.69)  10.819 ms
5  202.97.45.41 (202.97.45.41)  44.039 ms 202.97.65.197 (202.97.65.197)  33.670 ms 202.97.45.41 (202.97.45.41)  44.022 ms
6  202.97.34.114 (202.97.34.114)  38.876 ms * 202.97.26.57 (202.97.26.57)  39.196 ms
7  * * *
8  * 59.43.244.141 (59.43.244.141)  35.636 ms  35.619 ms
9  * * *
10  10.8.2.1 (10.8.2.1)  190.689 ms *  190.673 ms
11  172.246.0.233 (172.246.0.233)  204.794 ms  208.685 ms  197.576 ms
12  2.192-157-192.rdns.scalabledns.com (192.157.192.2)  191.417 ms  189.385 ms  187.726 ms
```

59.43.244.141 的whois信息显示：
```
Chinatelecom Next Carrying Network backbone
```

非CN2线路
```
shing@ubuntu:~$ traceroute github.com
traceroute to github.com (192.30.253.112), 30 hops max, 60 byte packets
1  Gargoyle.lan (192.168.1.1)  3.931 ms  3.914 ms  3.907 ms
2  1.20.88.218.broad.cd.sc.dynamic.163data.com.cn (218.88.20.1)  7.930 ms  7.922 ms  7.915 ms
3  * * 220.167.85.101 (220.167.85.101)  41.251 ms
4  171.208.203.89 (171.208.203.89)  10.906 ms 171.208.198.21 (171.208.198.21)  17.467 ms  17.459 ms
5  202.97.36.221 (202.97.36.221)  44.310 ms 202.97.65.197 (202.97.65.197)  35.062 ms  35.057 ms
6  202.97.35.210 (202.97.35.210)  41.199 ms  36.232 ms 202.97.35.190 (202.97.35.190)  41.028 ms
7  * 202.97.60.46 (202.97.60.46)  39.555 ms *
8  202.97.60.246 (202.97.60.246)  199.377 ms 202.97.52.138 (202.97.52.138)  191.059 ms 202.97.60.242 (202.97.60.242)  189.706 ms
9  202.97.49.154 (202.97.49.154)  212.195 ms 202.97.90.138 (202.97.90.138)  217.673 ms  217.648 ms
10  206.111.14.113 (206.111.14.113)  194.158 ms  207.343 ms  187.894 ms
11  207.88.14.212 (207.88.14.212)  252.905 ms
4.69.210.177 (4.69.210.177)  433.811 ms 207.88.14.212.ptr.us.xo.net (207.88.14.212)  261.285 ms
12  207.88.12.140.ptr.us.xo.net (207.88.12.140)  268.836 ms  297.371 ms *
13  * 207.88.12.180.ptr.us.xo.net (207.88.12.180)  254.920 ms  275.016 ms
14  207.88.12.209.ptr.us.xo.net (207.88.12.209)  270.114 ms  304.837 ms  280.934 ms
15  207.88.12.217.ptr.us.xo.net (207.88.12.217)  246.125 ms  249.268 ms *
16  207.88.12.214.ptr.us.xo.net (207.88.12.214)  272.297 ms  259.220 ms  282.742 ms
17  207.88.14.181.ptr.us.xo.net (207.88.14.181)  255.825 ms *  248.839 ms
```

202.97.60.246 whois 信息：
```
China Telecom backbone network
```

## 第二步 vps技术选择
---
推荐：https://www.budgetvm.com/xen-linux-vps.php
必须是 xen,vmware,kvm 之类的，不能是OpenVZ

必须具有内核操作权限，才具有优化空间

操作系统选择，查询锐速支持的类型和对应的内核

## 第三步 安装锐速
---
锐速就是保持连接重传，避免由于网络步稳定连接断开，这个是必须要安装的：
https://github.com/91yun/serverspeeder

## 第四步 安装shadowsocks
---
https://github.com/shadowsocks/shadowsocks-go

在vps上安装服务器端，在本机安装客户端。json文件参考
```
cat shadowsocks.json
{
    "server":"192.157.192.2",
    "server_port":33124,
    "local_address": "127.0.0.1",
    "local_port":1405,
    "password":"xxxxxxxxxxxxx",
    "timeout":300,
    "method":"aes-256-cfb"
}
```
### 1. 服务器端：
```
shadowsocks-server-linux64-1.1.5 -c ./shadowsocks.json
```

配置服务器端自动启动：
https://linuxconfig.org/how-to-automatically-execute-shell-script-at-startup-boot-on-systemd-linux

### 2. 客户端
```
shadowsocks-server-linux64-1.1.5 -c ./shadowsocks.json
```
mac下可以通过automator配置自启动程序，启动成功后就启动了socks5代理，然后指向客户端地址，如：127.0.0.1:1405即可。

### 3. http代理
由于socks5代理速度快，可以通过privoxy将http代理转为socks5代理，安装完后，找到config文件，然后注释掉原来的listen-address，在最后加入：
```
listen-address  0.0.0.0:8118
forward-socks5   /               127.0.0.1:1405 .
```

至此，本机http代理也声称，后续的http/https都可指向127.0.0.1:8118，或ip:8118地址

### pac设置
pac是一段自动代理脚本，可以根据ip和访问的url判断是否使用代理，可参考：
pac例子： http://s.xcai.net/pac.js

## minikube和docker配置
---
两者一般在国内都用不了，都需要设置代理，具体可以参考：
```
$ http_proxy=http://192.168.0.253:8118 minikube start \
         --docker-env HTTP_PROXY=http://192.168.0.253:8118 \
         --docker-env HTTPS_PROXY=http://192.168.0.253:8118 \
         --docker-env NO_PROXY=192.168.99.0/24 \
         --extra-config=apiserver.ServiceNodePortRange=1000-10000
```

[minikube/startMinikube.sh](../minikube/startMinikube.sh)有一个动态更新的脚本，根据本机地址动态替换启动minikube.