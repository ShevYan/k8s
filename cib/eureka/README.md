# EUREKA service - 1
用于服务注册和发现，需要最先启动

## 注意事项
- 启动的时候需要直到对方的地址，需要固定
- 其他容器使用的时候，需要外部指定eureka.client.serviceUrl.defaultZone，用于注册
- 启动时必须指定json环境变量，然后后面跟java -jar xxxx.jar
```
SPRING_APPLICATION_JSON='{
	"spring.profiles":"$HOSTNAME",
	"server.port":8761,
	"eureka.instance.hostname":"$HOSTNAME",
	"eureka.client.serviceUrl.defaultZone":"http://peer1:8761/eureka,http://peer2:8761/eureka"
}'
```

SPRING_APPLICATION_JSON={\
	"spring.profiles":"$(hostname)",\
	"server.port":8761,\
	"eureka.instance.hostname":"$(hostname)",\
	"eureka.client.serviceUrl.defaultZone":"http://peer1:8761/eureka,http://peer2:8761/eureka"\
}

```
SPRING_APPLICATION_JSON='{
	"spring.profiles":"peer1",
	"server.port":8661,
	"eureka.instance.hostname":"peer1",
	"eureka.client.serviceUrl.defaultZone":"http://peer1:8661/eureka,http://peer2:8662/eureka"
}'
```

```
SPRING_APPLICATION_JSON='{
	"spring.profiles":"peer2",
	"server.port":8662,
	"eureka.instance.hostname":"peer2",
	"eureka.client.serviceUrl.defaultZone":"http://peer1:8661/eureka,http://peer2:8662/eureka"
}'
```

## 负载均衡
两份，需要能够感知其他peer，所以peer地址要固定。对于client，可以在peer上加个LB固定地址访问，也可以不加，直接传入peer集合。
