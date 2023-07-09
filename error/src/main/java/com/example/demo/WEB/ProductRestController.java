package com.example.demo.WEB;


import com.example.demo.model.ProductDTO;
import com.example.demo.model.ProductErrorDTO;
import com.example.demo.model.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/procuts")
public class ProductRestController {

    @GetMapping("/{id}")

    public ResponseEntity<ProductDTO> getProductById(@PathVariable("id") long id){
        throw  new ProductNotFoundException(id);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ProductErrorDTO> onProductNotFound(ProductNotFoundException e){
        ProductErrorDTO productErrorDTO = new ProductErrorDTO(e.getId(), "Product not found");

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(productErrorDTO);

    }



}
