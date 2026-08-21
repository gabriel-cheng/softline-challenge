package com.gabriel.challenge.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.gabriel.challenge.domain.products.Products;
import com.gabriel.challenge.domain.products.ProductsRepository;
import com.gabriel.challenge.domain.products.RequestProducts;
import com.gabriel.challenge.domain.users.Users;

@RestController
@RequestMapping("/products")
public class ProductsController {

    private final ProductsRepository productsRepository;

    ProductsController(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    @GetMapping
    public ResponseEntity<List<Products>> getAllProducts(@AuthenticationPrincipal Users currentUser) {
        List<Products> ownProducts = productsRepository.findAllByUserId(currentUser.getUsersId());

        return ResponseEntity.status(HttpStatus.OK)
            .body(ownProducts);
    }

    @PostMapping
    public ResponseEntity<String> registerNewProduct(
        @RequestBody
        @Validated
        RequestProducts product,
        @AuthenticationPrincipal Users currentUser
    ) {
        Products newProduct = new Products(product);
        newProduct.setUserId(currentUser.getUsersId());

        productsRepository.save(newProduct);

        return ResponseEntity.status(HttpStatus.CREATED)
            .body("Product registered successfully!");
    }

    @PatchMapping("/{code}")
    public ResponseEntity<Products> updateProduct(
        @PathVariable int code,
        @RequestBody @Validated RequestProducts product,
        @AuthenticationPrincipal Users currentUser
    ) {
        Products productFound = productsRepository.findByCodeAndUserId(code, currentUser.getUsersId())
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Product not found!"
            ));

        if(product.description() != null) {
            productFound.setDescription(product.description());
        }

        if(product.bar_code() != null) {
            productFound.setBarCode(product.bar_code());
        }

        if(product.gross_weight() != null) {
            productFound.setGrossWeight(product.gross_weight());
        }

        if(product.net_weight() != null) {
            productFound.setNetWeight(product.net_weight());
        }

        Products productUpdated = productsRepository.save(productFound);

        return ResponseEntity.status(HttpStatus.OK)
            .body(productUpdated);
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<String> deleteProduct(
        @PathVariable int code,
        @AuthenticationPrincipal Users currentUser
    ) {
        Products productFound = productsRepository.findByCodeAndUserId(code, currentUser.getUsersId())
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Product not found!"
            ));

        productsRepository.delete(productFound);

        return ResponseEntity.status(HttpStatus.OK)
            .body("Product deleted successfully!");
    }

}
