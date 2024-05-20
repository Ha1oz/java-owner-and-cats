package net.haloz.producer;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import net.haloz.payload.SendingObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Producer {
    @Value("${spring.kafka.topic.name}")
    private String topicName;
    private static final Logger LOGGER = LoggerFactory.getLogger(Producer.class);
    private final KafkaTemplate<String, String> kafkaTemplate;

    @NonNull
    public void sendOwner(SendingObject sendingObject) {
//                Message<SendingObject> message = MessageBuilder
//                .withPayload(sendingObject)
//                .setHeader(KafkaHeaders.TOPIC, topicName)
//                .build();
        kafkaTemplate.send(topicName, sendingObject.toString());
        LOGGER.info(String.format("Owner info sent -> %s", sendingObject));
    }
}
