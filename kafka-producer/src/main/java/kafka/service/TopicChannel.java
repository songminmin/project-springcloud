package kafka.service;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface TopicChannel {
	
	String TOPIC_OUTPUT = "topic_output";
	
	String TOPIC_INPUT = "topic_input";
	
	@Output(TOPIC_OUTPUT)
	MessageChannel producerTopicMessage();

	@Input(TOPIC_INPUT)
	SubscribableChannel consumerTopicMessage();
}
