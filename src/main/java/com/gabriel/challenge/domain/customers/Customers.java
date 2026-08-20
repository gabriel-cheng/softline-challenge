package com.gabriel.challenge.domain.customers;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Table(name="customers")
@Entity(name="customers")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="code")
public class Customers {
    
    @Id
    @Column(name="code")
    private int code;

    private String name;

    private String nickname;

    private String document;

    private String address;

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return this.nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    
    public String getDocument() {
        return this.document;
    }

    public void setDocument(String document) {
        this.document = document;
    }
    
    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Customers(RequestCustomers requestCustomers) {
        this.code = requestCustomers.code();
        this.name = requestCustomers.name();
        this.nickname = requestCustomers.nickname();
        this.document = requestCustomers.document();
        this.address = requestCustomers.address();
    }

}
