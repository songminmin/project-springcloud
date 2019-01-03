package eureka.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class HealthConsumerController {

	@Autowired
    private RestTemplate restTemplate;

    @GetMapping(value = "/health")
    public String add() {
        return restTemplate.getForEntity("http://SONGMM/health/get", String.class).getBody();
    }

}