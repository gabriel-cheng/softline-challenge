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

import com.gabriel.challenge.domain.customers.Customers;
import com.gabriel.challenge.domain.customers.CustomersRepository;
import com.gabriel.challenge.domain.customers.RequestCustomers;
import com.gabriel.challenge.domain.users.Users;

@RestController
@RequestMapping("/customers")
public class CustomersController {

    private final CustomersRepository customersRepository;

    CustomersController(CustomersRepository customersRepository) {
        this.customersRepository = customersRepository;
    }

    @GetMapping
    public ResponseEntity<List<Customers>> getAllCustomers(@AuthenticationPrincipal Users currentUser) {
        List<Customers> ownCustomers = customersRepository.findAllByUserId(currentUser.getUsersId());

        return ResponseEntity.status(HttpStatus.OK)
            .body(ownCustomers);
    }

    @PostMapping
    public ResponseEntity<String> registerNewCustomer(
        @RequestBody
        @Validated
        RequestCustomers customer,
        @AuthenticationPrincipal Users currentUser
    ) {
        Customers newCustomer = new Customers(customer);
        newCustomer.setUserId(currentUser.getUsersId());

        customersRepository.save(newCustomer);

        return ResponseEntity.status(HttpStatus.CREATED)
            .body("Customer registered successfully!");
    }

    @PatchMapping("/{code}")
    public ResponseEntity<Customers> updateCustomer(
        @PathVariable int code,
        @RequestBody @Validated RequestCustomers customer,
        @AuthenticationPrincipal Users currentUser
    ) {
        Customers customerFound = customersRepository.findByCodeAndUserId(code, currentUser.getUsersId())
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Customer not found!"
            ));

        if(customer.name() != null) {
            customerFound.setName(customer.name());
        }

        if(customer.nickname() != null) {
            customerFound.setNickname(customer.nickname());
        }

        if(customer.document() != null) {
            customerFound.setDocument(customer.document());
        }

        if(customer.address() != null) {
            customerFound.setAddress(customer.address());
        }

        Customers customerUpdated = customersRepository.save(customerFound);

        return ResponseEntity.status(HttpStatus.OK)
            .body(customerUpdated);
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<String> deleteCustomer(
        @PathVariable int code,
        @AuthenticationPrincipal Users currentUser
    ) {
        Customers customerFound = customersRepository.findByCodeAndUserId(code, currentUser.getUsersId())
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Customer not found!"
            ));

        customersRepository.delete(customerFound);

        return ResponseEntity.status(HttpStatus.OK)
            .body("Customer deleted successfully!");
    }

}
