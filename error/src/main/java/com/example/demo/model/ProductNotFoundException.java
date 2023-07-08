package com.example.demo.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Product not found")
public class ProductNotFoundException extends RuntimeException {

    private long id;


    public ProductNotFoundException(long id) {
     super("Product with ID " + id +" not found");
     this.id = id;
    }


    public long getId() {
        return id;
    }
}
