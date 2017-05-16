package cn.ghostcloud.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDiscoveryClient
@SpringBootApplication
public class PaymentwebApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymentwebApplication.class, args);
	}
}
