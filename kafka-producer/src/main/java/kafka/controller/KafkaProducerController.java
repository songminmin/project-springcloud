package kafka.controller;

import javax.annotation.Resource;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kafka.service.TopicChannel;

@RestController
@RequestMapping("/kafka/producer")
@EnableBinding(Source.class)
public class KafkaProducerController {

	/*@Autowired
	private Source source;*/

	@Resource(name = TopicChannel.TOPIC_OUTPUT_A)
	private MessageChannel channelA;
	
	@Resource(name = TopicChannel.TOPIC_OUTPUT_B)
	private MessageChannel channelB;

	@PostMapping("/A")
	public String producerKafkaA(@RequestBody String body){
		boolean isSendSuccess = channelA.send(MessageBuilder.withPayload(body).build());
		return isSendSuccess ? "发送成功" : "发送失败";
	}
	
	@PostMapping("/B")
	public String producerKafkaB(@RequestBody String body){
		boolean isSendSuccess = channelB.send(MessageBuilder.withPayload(body).build());
		return isSendSuccess ? "发送成功" : "发送失败";
	}
	
	@StreamListener(TopicChannel.TOPIC_INPUT_A)
    public void receiveA(Message<String> message) {
        System.out.println("ConumserA receive topic: " + message.getPayload());
    }
	
	@StreamListener(TopicChannel.TOPIC_INPUT_B)
    public void receiveB(Message<String> message) {
        System.out.println("ConumserB receive topic: " + message.getPayload());
    }

}
