package com.jpql.module.jpql;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@Entity
public class Product {

    @Id @GeneratedValue
    private Long id;

    private String name;

    private int price;

    private int stockAmount;

}
