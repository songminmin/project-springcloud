package eureka.client.service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import eureka.client.service.fallback.HelloFallback;

@FeignClient(value="SONGMM", fallback = HelloFallback.class)
public interface HelloFeignService {
	
	@RequestMapping(value="/health/get", method= RequestMethod.GET)
    public String get();
	
}
