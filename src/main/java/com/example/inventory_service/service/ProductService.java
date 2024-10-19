package com.example.inventory_service.service;


import com.example.inventory_service.model.Product;
import com.example.inventory_service.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {


    @Autowired
    private ProductRepository productRepository;


    public Product addProduct(Product product)
    {
        return (Product) productRepository.save(product);

    }

    public List<Product> getAllProducts(){

        return  productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id){
        return productRepository.findById(id);
    }

    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }

    public Product updateProduct(Long id, Product updatedProduct){

        Optional<Product> productOptional=productRepository.findById(id);
        if(productOptional.isPresent()){
            Product existingProduct=productOptional.get();
            existingProduct.setName(updatedProduct.getName());
            existingProduct.setDescription(updatedProduct.getDescription());
            existingProduct.setPrice(updatedProduct.getPrice());
            existingProduct.setQuantity(updatedProduct.getQuantity());

        }

        return null;

    }




}
