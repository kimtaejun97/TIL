package com.itemservice.domain.item.dto;

import com.itemservice.domain.item.Item;
import lombok.Data;

@Data
public class SaveItemDto {
    private String itemName;
    private Integer price;
    private Integer quantity;

    public Item toEntity() {
        return new Item(itemName, price, quantity);
    }
}
