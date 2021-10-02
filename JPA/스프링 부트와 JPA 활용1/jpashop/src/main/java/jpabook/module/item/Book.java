package jpabook.module.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Getter @Setter
@Entity
public class Book extends Item{

    private String author;
    private String isbn;
}
