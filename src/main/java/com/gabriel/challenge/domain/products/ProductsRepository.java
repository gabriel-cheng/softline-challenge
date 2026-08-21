package com.gabriel.challenge.domain.products;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepository extends JpaRepository<Products, Integer> {

    List<Products> findAllByUserId(String userId);

    Optional<Products> findByCodeAndUserId(Integer code, String userId);

}
