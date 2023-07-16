package com.example.EventsDemo.model;

import org.springframework.context.ApplicationEvent;

import java.util.ArrayList;
import java.util.List;

public class OrderCreatedEvent extends ApplicationEvent {

    private List<Long> AllProductIds = new ArrayList<>();

    public OrderCreatedEvent(Object source) {
        super(source);
    }



    public List<Long> getAllProductIds() {
        return AllProductIds;
    }

    public void setAllProductIds(List<Long> allProductIds) {
        AllProductIds = allProductIds;
    }


    public OrderCreatedEvent addProductId(Long productId) {
         this.AllProductIds.add(productId);
        return this;
    }
}
