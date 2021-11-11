package jpabook.module.item.dto;

import lombok.Data;

@Data
public class UpdateItemDto {
    private String name;
    private int price;
    private int stockQuantity;
    private String author;
    private String isbn;
}
