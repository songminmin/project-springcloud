package kafka.service;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface TopicChannel {
	
	String TOPIC_OUTPUT_A = "producerA";
	
	String TOPIC_OUTPUT_B = "producerB";
	
	String TOPIC_INPUT_A = "consumerA";
	
	String TOPIC_INPUT_B = "consumerB";
	
	@Output(TOPIC_OUTPUT_A)
	MessageChannel producerTopicMessageA();
	
	@Output(TOPIC_OUTPUT_B)
	MessageChannel producerTopicMessageB();

	@Input(TOPIC_INPUT_A)
	SubscribableChannel consumerTopicMessageA();
	
	@Input(TOPIC_INPUT_B)
	SubscribableChannel consumerTopicMessageB();
}
