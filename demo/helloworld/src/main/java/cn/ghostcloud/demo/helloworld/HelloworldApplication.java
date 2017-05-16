package cn.ghostcloud.demo.helloworld;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class HelloworldApplication {
	Logger logger = LoggerFactory.getLogger(HelloworldApplication.class);
	@RequestMapping("/")
	public String hello() {
		logger.error("[CIB]", new RuntimeException("Test Application"));
		logger.info("[CIB]-aaaaabbbbcccddd");
		return "hello, world!";
	}

	public static void main(String[] args) {
		SpringApplication.run(HelloworldApplication.class, args);
	}
}
