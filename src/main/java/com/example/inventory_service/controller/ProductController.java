package com.example.inventory_service.controller;

import com.example.inventory_service.model.Product;
import com.example.inventory_service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class ProductController {


    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product){

        Product createProduct=productService.addProduct(product);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(){
        List<Product> listProduct=productService.getAllProducts();

        return new ResponseEntity<>(listProduct,HttpStatus.OK);

    }


    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id){
     return  productService.getProductById(id)
                .map( product -> new ResponseEntity<>(product,HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product updateProduct){
        Product product= productService.updateProduct(id,updateProduct);

        if (product!=null){
            return new ResponseEntity<>(product,HttpStatus.OK);

        }
        else {
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
