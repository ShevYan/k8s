# PAYMENT service - 6

最终的服务，需要由zuul代理。

## 负载均衡

直接注册连接到zuul，不需要特殊处理，只要有多个副本即可。