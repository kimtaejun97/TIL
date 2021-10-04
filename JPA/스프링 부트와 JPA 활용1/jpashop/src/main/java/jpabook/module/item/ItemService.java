package jpabook.module.item;

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
    public Item saveItem(Item item){
        return itemRepository.save(item);
    }

    public Item findById(Long id){
        return itemRepository.findById(id);
    }

    public List<Item> findItems(){
        return itemRepository.findAll();
    }
}
