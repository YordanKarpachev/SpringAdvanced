package com.example.EventsDemo.service;

import com.example.EventsDemo.model.OrderCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class DispositionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DispositionService.class);

    @EventListener(OrderCreatedEvent.class)

    public void onOrderCreated(OrderCreatedEvent event) {

        LOGGER.info("Disposition for added for products {} ", event.getAllProductIds());
    }
}
