package com.example.demo.WEB;


import com.example.demo.model.ProductNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CategoryController {

    @GetMapping("/categories/{id}")
    public String getCategoryById(@PathVariable("id") Long id) {
        throw new ProductNotFoundException(id);
    }

}
