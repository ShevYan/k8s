package cn.ghostcloud.demo.paymentweb;

import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.zuul.ZuulConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@EnableDiscoveryClient
@RestController
@SpringBootApplication
@EnableFeignClients
public class PaymentwebApplication {

    @FeignClient("zuul")
    interface ZuulClient {
        @RequestMapping("/api/payment")
        String callZuul();
    }

    @Autowired
    ZuulClient client;

	@RequestMapping("/paymentweb")
	public String paymentweb() {
		return "Call payment from zull:" + client.callZuul();
	}

	public static void main(String[] args) {
		SpringApplication.run(PaymentwebApplication.class, args);
	}


}
