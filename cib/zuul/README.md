# ZUUL service - 5

自带的负载均衡服务，可以从eureka中去识别，然后生成配置。

## 负载均衡

如果是内部可以使用feign，结合client side LB。外部访问需要再加一层固定地址.
