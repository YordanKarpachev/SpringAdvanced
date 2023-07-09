package com.example.demo.model;

public class ProductErrorDTO {

    private long productId;

    private String description;

    public ProductErrorDTO(long productId, String description) {
        this.productId = productId;
        this.description = description;
    }

    public ProductErrorDTO() {
    }



    public long getProductId() {
        return productId;
    }

    public String getDescription() {
        return description;
    }
}
