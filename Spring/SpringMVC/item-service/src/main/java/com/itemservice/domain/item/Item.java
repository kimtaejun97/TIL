package com.itemservice.domain.item;


import com.itemservice.domain.item.dto.UpdateItemDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@NoArgsConstructor
@Getter @Setter
public class Item {

    private Long id;
    private String itemName;
    private Integer price; // 기본값을 0이 아닌 null로 하기 위해.
    private Integer quantity ;

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }

    public void update(UpdateItemDto updateItemDto) {
        this.itemName = updateItemDto.getItemName();
        this.price = updateItemDto.getPrice();
        this.quantity = updateItemDto.getQuantity();
    }

    public String getEncodedId() {
        return URLEncoder.encode(String.valueOf(id), StandardCharsets.UTF_8);
    }
}
