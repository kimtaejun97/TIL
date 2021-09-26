package com.shop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter @Setter
@Embeddable
public class Address {
    private String city;

    private String street;

    @Column(name = "zcode")
    private String zipcode;
}
