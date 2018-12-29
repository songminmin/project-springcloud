package eureka.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eureka.client.service.feign.GatewayFeignService;

@RestController
@RequestMapping("/gateway")
public class GatewayConsumerController {
	
	@Autowired
	private GatewayFeignService gatewayFeignService;
	
	private final static String TARGET_IP_PORT = "http://localhost:7900";
	private final static String SERVICE_ID = "serviceId_";
	private final static String PATH = "/path";
	
	@GetMapping
	public Object addGateway(String id, Integer replenishRate, Integer burstCapacity){
		String serviceId = SERVICE_ID + id;
		String path = PATH + id;
		gatewayFeignService.addGateway(serviceId, path, TARGET_IP_PORT, replenishRate, burstCapacity);
		return "addOk";
	}
	
}
