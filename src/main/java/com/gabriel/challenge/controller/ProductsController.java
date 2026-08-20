package com.gabriel.challenge.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gabriel.challenge.domain.products.Products;
import com.gabriel.challenge.domain.products.ProductsRepository;

@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductsRepository productsRepository;

    @GetMapping
    public ResponseEntity<List<Products>> getAllProducts() {
        List<Products> allProducts = productsRepository.findAll();

        return ResponseEntity.status(HttpStatus.OK)
            .body(allProducts);
    }

}
