package com.example.demo.WEB;


import com.example.demo.model.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProductController {

    @GetMapping("/products/{id}")
    public String getProductById(@PathVariable("id") Long id) {

        throw new ProductNotFoundException(id);
    }

    @GetMapping("/products")
    public String getProducts() {
        throw new NullPointerException("Oppps bug");
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(ProductNotFoundException.class)
    public ModelAndView onProductNotFound(ProductNotFoundException e) {
        ModelAndView modelAndView = new ModelAndView("product-not-found");

        modelAndView.addObject("productId", e.getId());
        return modelAndView;
    }
}
