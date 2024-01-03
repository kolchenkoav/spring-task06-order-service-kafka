package com.example.orderservice.web.controller;


import com.example.orderservice.model.Order;
import com.example.orderservice.service.KafkaMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/kafka")
@RequiredArgsConstructor
public class KafkaController {
    @Value("${app.kafka.kafkaMessageTopic}")
    private String topicName;

    private final KafkaTemplate<String, Order> kafkaTemplate;

    private final KafkaMessageService kafkaMessageService;

    @PostMapping("/send")
    public ResponseEntity<String> sendMessage(@RequestBody Order order) {
        kafkaTemplate.send(topicName, order);

        return ResponseEntity.ok("Message send to kafka");
    }

    @GetMapping("/{product}")
    public ResponseEntity<Order> getByProduct(@PathVariable String product) {
        return ResponseEntity.ok(kafkaMessageService.getByProduct(product).orElseThrow());
    }
}
