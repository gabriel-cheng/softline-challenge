package com.gabriel.challenge.domain.customers;

public record RequestCustomers(
    Integer code,
    String name,
    String nickname,
    String document,
    String address
) { }
