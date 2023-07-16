package com.example.EventsDemo.service;


import com.example.EventsDemo.model.OrderCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class BonusPointService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BonusPointService.class);

    @EventListener(OrderCreatedEvent.class)

    public void onOrderCreated(OrderCreatedEvent event) {

        LOGGER.info("Bonus points added for products {} ", event.getAllProductIds());
    }
}
