package com.example.orderservice.service;

import com.example.orderservice.model.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaMessageService {
    private final KafkaTemplate<String, Order> kafkaTemplate;

    @Value("${app.kafka.kafkaOrderTopic}")
    private String topicName;

    public void send(Order order) {
        kafkaTemplate.send(topicName, order);
    }
}
