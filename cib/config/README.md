# CONFIG service - 2
中心配置服务，可以按名字来区分不同服务的配置，比如一个服务的spring.application.name=abcd，那么只要在git.uri目录下保存abcd.properties，就能自动获得统一的配置。

## 注意事项
启动时，需要指定eureka的defaultZone地址
```
SPRING_APPLICATION_JSON='{
	"eureka.client.serviceUrl.defaultZone":"http://peer1:8661/eureka,http://peer2:8662/eureka"
}'
```

## 负载均衡

一份即可，多了还要考虑同步问题