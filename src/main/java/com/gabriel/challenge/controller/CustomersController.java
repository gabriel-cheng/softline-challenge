package com.gabriel.challenge.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gabriel.challenge.domain.customers.Customers;
import com.gabriel.challenge.domain.customers.CustomersRepository;

@RestController
@RequestMapping("/customers")
public class CustomersController {

    @Autowired
    private CustomersRepository customersRepository;

    @GetMapping
    public ResponseEntity<List<Customers>> getAllCustomers() {
        List<Customers> allCustomers = customersRepository.findAll();

        return ResponseEntity.status(HttpStatus.OK)
            .body(allCustomers);
    }

}
