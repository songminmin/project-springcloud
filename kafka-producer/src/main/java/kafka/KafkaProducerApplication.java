package kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.stream.annotation.EnableBinding;

import kafka.service.TopicChannel;

@SpringBootApplication
@EnableEurekaClient
@EnableBinding(TopicChannel.class)
public class KafkaProducerApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(KafkaProducerApplication.class, args);
	}

}
