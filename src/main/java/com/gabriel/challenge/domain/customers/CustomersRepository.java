package com.gabriel.challenge.domain.customers;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomersRepository extends JpaRepository<Customers, Integer> {

    List<Customers> findAllByUserId(String userId);

    Optional<Customers> findByCodeAndUserId(Integer code, String userId);

}
