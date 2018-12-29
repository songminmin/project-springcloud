package eureka.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = {"eureka.client"})
@ComponentScan("eureka.client")
@EnableCircuitBreaker//打开Hystrix断路器
public class EurekaConsumerClientApplication {
	
	@Bean
    @LoadBalanced
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
	
	public static void main(String[] args) {
//		new SpringApplicationBuilder(EurekaClientConsumerApplication.class).web(true).run(
//                args);
		SpringApplication.run(EurekaConsumerClientApplication.class);
	}

}
