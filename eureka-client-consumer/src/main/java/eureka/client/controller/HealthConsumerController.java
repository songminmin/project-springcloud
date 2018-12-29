package eureka.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.HystrixCircuitBreaker;
import com.netflix.hystrix.HystrixCommandKey;

import eureka.client.service.feign.HelloFeignService;

@RestController
public class HealthConsumerController {

	@Autowired
	private HelloFeignService helloService;

	@RequestMapping(value="/health")
	public String getHealth(){
		String result = helloService.get();

		HystrixCircuitBreaker breaker = HystrixCircuitBreaker.Factory
				.getInstance(HystrixCommandKey.Factory
						.asKey("HelloFeignService#get()"));
		System.out.println("断路器状态：" + breaker.isOpen());
		return result;
	}
	
}
