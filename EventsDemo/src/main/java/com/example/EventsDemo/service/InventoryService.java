package com.example.EventsDemo.service;

import com.example.EventsDemo.model.OrderCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;

public class InventoryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(InventoryService.class);

    @EventListener(OrderCreatedEvent.class)

    public void onOrderCreated(OrderCreatedEvent event) {

        LOGGER.info("Inventory subtracted for added for products {} ", event.getAllProductIds());
    }


}
