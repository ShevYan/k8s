package cn.ghostcloud.demo.payment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableDiscoveryClient
@RestController
@SpringBootApplication
public class PaymentApplication {

	@RequestMapping("/")
	public String payment() {
		return "payment";
	}

	public static void main(String[] args) {
		SpringApplication.run(PaymentApplication.class, args);
	}
}
