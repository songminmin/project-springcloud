package config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/*
 * 配置文件名需要 ${spring.application.name}.${spring.cloud.config.profile}
 * 	    反之如果都不是对的话默认取 application.${spring.cloud.config.profile}
 * 
 * 
 * refreshUri: http://localhost:7800/actuator/refresh
 */
@SpringBootApplication
@EnableEurekaClient
public class ConfigClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigClientApplication.class, args);
	}

}
