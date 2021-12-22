package com.itemservice.domain.item;


import com.itemservice.domain.item.dto.UpdateItemDto;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public class ItemRepository {

    private static final Map<Long, Item> store = new ConcurrentHashMap<>();
    private static long sequence = 0L;

    public Item save(Item item){
        item.setId(++sequence);
        store.put(item.getId(), item);

        return item;
    }

    public Item findById(Long id){
        return store.get(id);
    }

    public List<Item> findAll(){
        return new ArrayList<>(store.values());
    }

    public void update(Long itemId, UpdateItemDto updateItemDto){
        Item findItem = findById(itemId);
        findItem.update(updateItemDto);
    }

    public void deleteById(Long itemId){
        store.remove(itemId);
    }

    public void clearStore(){
        store.clear();
    }
}

