package com.gabriel.challenge.domain.products;

public record RequestProducts(
    int code,
    String description,
    String bar_code,
    float gross_weight,
    float net_weight
) { }
