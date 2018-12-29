package eureka.client.service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import eureka.client.service.fallback.GatewayFallback;

@FeignClient(value="GATEWAY", fallback = GatewayFallback.class)
public interface GatewayFeignService {
	
	@RequestMapping(value="/gateway", method= RequestMethod.POST)
    public String addGateway(@RequestParam(value="id") String id, @RequestParam(value="path") String path, 
    		@RequestParam(value="replenishRate") Integer replenishRate, 
    		@RequestParam(value="burstCapacity") Integer burstCapacity);
	
}
