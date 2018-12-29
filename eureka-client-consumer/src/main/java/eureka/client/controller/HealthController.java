package eureka.client.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
public class HealthController {
	
	@GetMapping("/{pathId}")
	public String showPathId(@PathVariable("pathId") String pathId){
		return "7901---" + pathId;
	}
}
