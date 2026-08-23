package com.gabriel.challenge.domain.customers;

import jakarta.validation.constraints.Size;

public record RequestCustomers(
    Integer code,
    @Size(max = 100, message = "must be at most 60 characters") String name,
    @Size(max = 50, message = "must be at most 100 characters") String nickname,
    @Size(max = 20, message = "must be at most 20 characters") String document,
    @Size(max = 200, message = "must be at most 200 characters") String address
) { }
