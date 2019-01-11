package kafka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka/producer")
@EnableBinding(Source.class)
public class KafkaProducerController {

	@Autowired
	private Source source;
	
	@PostMapping
	public String producerKafka(){
		source.output().send(MessageBuilder.withPayload("aaa").build());
		return "ok";
	}
	
}
