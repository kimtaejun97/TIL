package com.shop.domain;

import com.fasterxml.jackson.databind.ser.Serializers;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
public class CategoryItem extends BaseEntity {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    public CategoryItem() {
    }
}
