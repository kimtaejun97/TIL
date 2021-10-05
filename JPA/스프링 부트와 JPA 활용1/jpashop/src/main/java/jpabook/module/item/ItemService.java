package jpabook.module.item;

import jpabook.module.item.dto.UpdateItemDto;
import jpabook.module.item.form.BookForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public Long saveItem(Item item){
        return itemRepository.save(item).getId();
    }

    public Item findById(Long id){
        return itemRepository.findById(id);
    }

    public List<Item> findItems(){
        return itemRepository.findAll();
    }

    @Transactional
    public void updateItem(Long itemId, UpdateItemDto updateItemDto) {
        Book book = (Book)itemRepository.findById(itemId);
        book.changeProperties(updateItemDto);
    }
}
