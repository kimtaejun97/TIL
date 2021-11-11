package jpabook.module.item.form;

import jpabook.module.item.Book;
import jpabook.module.item.Item;
import jpabook.module.item.ItemService;
import jpabook.module.item.dto.UpdateItemDto;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class BookForm {

    private Long id;
    private String name;
    private int price;
    private int stockQuantity;

    private String author;
    private String isbn;

    public static BookForm createFormFromEntity(Book bookEntity) {
        BookForm bookForm = new BookForm();
        bookForm.setPrice(bookEntity.getPrice());
        bookForm.setName(bookEntity.getName());
        bookForm.setStockQuantity(bookEntity.getStockQuantity());
        bookForm.setIsbn(bookEntity.getName());
        bookForm.setAuthor(bookEntity.getAuthor());
        bookForm.setId(bookEntity.getId());

        return bookForm;
    }

    public Item toEntity() {
        Book book = new Book();
        book.setStockQuantity(stockQuantity);
        book.setAuthor(author);
        book.setIsbn(isbn);
        book.setName(name);
        book.setPrice(price);
        book.setId(id);

        return book;
    }

    public UpdateItemDto toDto(){
        UpdateItemDto dto = new UpdateItemDto();
        dto.setStockQuantity(stockQuantity);
        dto.setAuthor(author);
        dto.setIsbn(isbn);
        dto.setName(name);
        dto.setPrice(price);

        return dto;
    }
}
