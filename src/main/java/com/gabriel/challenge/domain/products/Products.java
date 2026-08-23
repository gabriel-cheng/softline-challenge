package com.gabriel.challenge.domain.products;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Table(name="products")
@Entity(name="products")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="code")
public class Products {

    @Id
    @Column(name="code")
    private Integer code;

    private String description;

    @Column(unique = true, nullable = false)
    private String bar_code;

    @Column(nullable = false)
    private BigDecimal selling_price;

    private float gross_weight;
    
    private float net_weight;

    public int getCode() {
        return this.code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBarCode() {
        return this.bar_code;
    }

    public void setBarCode(String bar_code) {
        this.bar_code = bar_code;
    }

    public BigDecimal getSellingPrice() {
        return this.selling_price;
    }

    public void setSellingPrice(BigDecimal selling_price) {
        this.selling_price = selling_price;
    }

    public float getGrossWeight() {
        return this.gross_weight;
    }

    public void setGrossWeight(float gross_weight) {
        this.gross_weight = gross_weight;
    }

    public float getNetWeight() {
        return this.net_weight;
    }

    public void setNetWeight(float net_weight) {
        this.net_weight = net_weight;
    }

    public Products(RequestProducts requestProducts) {
        this.code = requestProducts.code();
        this.description = requestProducts.description();
        this.bar_code = requestProducts.bar_code();
        this.selling_price = requestProducts.selling_price();
        this.gross_weight = requestProducts.gross_weight();
        this.net_weight = requestProducts.net_weight();
    }

}
