package com.gabriel.challenge.domain.products;

public record RequestProducts(
    Integer code,
    String description,
    String bar_code,
    Float gross_weight,
    Float net_weight
) { }
