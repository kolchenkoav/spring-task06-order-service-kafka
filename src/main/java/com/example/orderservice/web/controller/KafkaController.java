package com.example.orderservice.web.controller;

import com.example.orderservice.model.Order;
import com.example.orderservice.service.KafkaMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * эндпоинт, на который приходит POST-запрос с сущностью Order.
 * Сущность Order состоит из двух полей: String product и Integer quantity.
 * Эндпоинт принимает сущность и отправляет в Kafka событие OrderEvent
 * (которое также состоит из полей product и quantity).
 * Событие отправляется в топик order-topic.
 */
@RestController
@RequestMapping("/api/v1/kafka")
@RequiredArgsConstructor
public class KafkaController {
    private final KafkaMessageService service;

    @PostMapping("/send")
    public ResponseEntity<String> sendMessage(@RequestBody Order order) {
        service.send(order);
        return ResponseEntity.ok("Order send to kafka");
    }
}
