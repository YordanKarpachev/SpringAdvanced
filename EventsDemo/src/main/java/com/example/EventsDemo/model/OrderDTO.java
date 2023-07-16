package com.example.EventsDemo.model;

import java.util.ArrayList;
import java.util.List;

public class OrderDTO {

    private List<Long> AllProductIds = new ArrayList<>();


    public List<Long> getAllProductIds() {
        return AllProductIds;
    }

    public void setAllProductIds(List<Long> allProductIds) {
        AllProductIds = allProductIds;
    }


    public OrderDTO addProductId(Long productId) {
        this.AllProductIds.add(productId);
        return this;
    }
}
