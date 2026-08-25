package com.gabriel.challenge.domain.products;

import java.math.BigDecimal;

import jakarta.validation.constraints.Size;

public record RequestProducts(
    Integer code,
    @Size(max = 60, message = "muste be at most 60 characters") String description,
    @Size(max = 14, message = "muste be at most 14 characters") String bar_code,
    BigDecimal selling_price,
    BigDecimal gross_weight,
    BigDecimal net_weight
) { }
