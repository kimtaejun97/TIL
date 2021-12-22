package com.itemservice.domain.item.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UpdateItemDto {
    private String itemName;
    private Integer price;
    private Integer quantity;

    public UpdateItemDto(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }

}
