package com.example.orderservice.service;

import com.example.orderservice.model.Order;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class KafkaMessageService {
    private final List<Order> orders = new ArrayList<>();

    public void add(Order order) {
        orders.add(order);
    }

    public Optional<Order> getByProduct(String product) {
        return orders.stream().filter(mes -> mes.getProduct().equals(product)).findFirst();
    }
}
