package com.gabriel.challenge.domain.customers;

public record RequestCustomers(
    int code,
    String name,
    String nickname,
    String document,
    String address
) { }
