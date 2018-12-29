package eureka.client.service.fallback;

import org.springframework.stereotype.Component;

import eureka.client.service.feign.HelloFeignService;

@Component
public class HelloFallback implements HelloFeignService{

	@Override
	public String get() {
		return "fallback hello get";
	}

}
