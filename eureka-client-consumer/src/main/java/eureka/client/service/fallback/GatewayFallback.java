package eureka.client.service.fallback;

import org.springframework.stereotype.Component;

import eureka.client.service.feign.GatewayFeignService;

@Component
public class GatewayFallback implements GatewayFeignService{

	@Override
	public String addGateway(String id, String path, Integer replenishRate,
			Integer burstCapacity) {
		return "fallback addGateway";
	}

}
