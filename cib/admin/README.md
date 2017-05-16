# ADMIN service -3

一个第三方监控服务，可以查看状态，集成了一些actuator功能。

## 注意事项

启动时，需要指定eureka的defaultZone地址
```
SPRING_APPLICATION_JSON='{
	"eureka.client.serviceUrl.defaultZone":"http://peer1:8661/eureka,http://peer2:8662/eureka"
}'
```

## 负载均衡
只需要一份即可，只要保证能够高可用