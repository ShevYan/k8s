package cn.ghostcloud.demo.eureka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EurekaInstanceConfigBean;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableEurekaServer
@EnableDiscoveryClient
@SpringBootApplication
@RestController
public class EurekaApplication {

	@Autowired
	EurekaInstanceConfigBean config;

	@RequestMapping("/config")
	public String hello() {
		return config.toString();
	}

	public static void main(String[] args) {
		SpringApplication.run(EurekaApplication.class, args);
	}

}
