package net.haloz.service;

import net.haloz.payload.ResponseObject;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Collections;
import java.util.UUID;

@Service
public class ClientService {

    private final static Logger LOGGER = LoggerFactory.getLogger(ClientService.class);
    @Value("${spring.kafka.topic.name}")
    private String topic;
    private final KafkaConsumer<String,String> consumer;

    public ClientService(KafkaConsumer<String, String> consumer) {
        this.consumer = consumer;
    }

    public String getMessageWithCorrectUUID(String uuid_string) {
        consumer.subscribe(Collections.singletonList(topic));
        LOGGER.info("uuid_string -> " + uuid_string);
        String result = "";
        for (ConsumerRecord<String, String> record : consumer.poll(Duration.ofSeconds(10))) {
            ResponseObject responseObject = new ResponseObject(record.value());
            LOGGER.info("Response object -> " + responseObject);
            if (responseObject.getGlobalId().equals(uuid_string)) {
                LOGGER.info("Response -> " + record.value());
                result = record.value();
            }
        }

        consumer.seekToBeginning(consumer.assignment());
        return result;
    }
    public String getAll() {
        consumer.subscribe(Collections.singletonList(topic));

        StringBuilder result = new StringBuilder();
        for (ConsumerRecord<String, String> record : consumer.poll(Duration.ofSeconds(10))) {
            LOGGER.info("Response -> " + record.value());
            result.append(record.value());
            result.append("\n");
        }
        return result.toString();
    }

}
