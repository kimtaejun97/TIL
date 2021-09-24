package com.shop.domain;

import com.fasterxml.jackson.databind.ser.Serializers;

import javax.persistence.*;

@Entity
public class CategoryItem extends BaseEntity {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;
}
