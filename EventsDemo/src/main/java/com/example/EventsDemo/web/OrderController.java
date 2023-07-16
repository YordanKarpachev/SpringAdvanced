package com.example.EventsDemo.web;

import com.example.EventsDemo.model.OrderDTO;
import com.example.EventsDemo.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Random;

@Controller
public class OrderController {

    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders/dummy/create")
    void createDummyOrder() {

        OrderDTO dummyOrderDTO = new OrderDTO();

        Random random = new Random();

        for (int i = 0; i < 3; i++) {

            dummyOrderDTO.addProductId(random.nextLong(100));
        }

        orderService.createOrder(dummyOrderDTO);

    }


}
