package net.haloz.request;

import org.apache.kafka.common.protocol.types.Field;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RequestSender {
    private final RestTemplate restTemplate;

    public RequestSender(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }
    public String sendPublishCatToTopic(Long id) {
        String url = "http://localhost:8070/cat/publish/{id}";
        return this.restTemplate.getForObject(url, String.class, id);
    }
    public String sendPublishOwnerToTopic(Long id) {
        String url = "http://localhost:8060/owner/publish/{id}";
        return this.restTemplate.getForObject(url, String.class, id);
    }
}
