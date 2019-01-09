package eureka.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;

/*
 * 仪表盘 http://localhost:7901/hystrix
 * 监听地址 http://localhost:7901/hystrix.stream
 * 
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = {"eureka.client"})
@ComponentScan("eureka.client")
@EnableCircuitBreaker//打开Hystrix断路器
@EnableHystrixDashboard
public class EurekaConsumerFeignClientApplication {
	
	@Bean
    @LoadBalanced
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
	
	@Bean
    public ServletRegistrationBean<HystrixMetricsStreamServlet> getServlet() {
        HystrixMetricsStreamServlet servlet = new HystrixMetricsStreamServlet();
        ServletRegistrationBean<HystrixMetricsStreamServlet> bean = new ServletRegistrationBean<>(servlet);
        bean.addUrlMappings("/hystrix.stream");
        bean.setName("HystrixMetricsStreamServlet");
        return bean;
    }
	
	public static void main(String[] args) {
//		new SpringApplicationBuilder(EurekaClientConsumerApplication.class).web(true).run(
//                args);
		SpringApplication.run(EurekaConsumerFeignClientApplication.class);
	}

}
