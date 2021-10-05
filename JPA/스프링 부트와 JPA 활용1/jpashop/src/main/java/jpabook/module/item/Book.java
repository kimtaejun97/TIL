package jpabook.module.item;

import jpabook.module.item.dto.UpdateItemDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Getter @Setter
@Entity
public class Book extends Item{

    private String author;
    private String isbn;

    public void changeProperties(UpdateItemDto updateItemDto) {
        super.setName(updateItemDto.getName());
        super.setPrice(updateItemDto.getPrice());
        super.setStockQuantity(updateItemDto.getStockQuantity());
        this.author = updateItemDto.getAuthor();
        this.isbn = updateItemDto.getIsbn();
    }
}
