package gateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gateway.route.DynamicRouteService;

@RestController
@RequestMapping("/gateway")
public class GatewayController {
	
	@Autowired
	DynamicRouteService dynamicRouteService;
	
	@PostMapping
	public Object addGateway(String id, String path, String targetIpPort, Integer replenishRate, Integer burstCapacity){
		dynamicRouteService.addGateway(id, path, replenishRate, burstCapacity);
		return "addOk";
	}
	
}
