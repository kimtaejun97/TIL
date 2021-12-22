package com.itemservice.domain.item;

import com.itemservice.domain.item.dto.UpdateItemDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void after(){
        itemRepository.clearStore();
    }

    @Test
    void save() throws Exception {
        // given
        Item saveditem = new Item("ItemA", 10000, 10);

        // when
        itemRepository.save(saveditem);

        // then
        Item byId = itemRepository.findById(saveditem.getId());
        assertThat(byId).isEqualTo(saveditem);
    }

    @Test
    void findAll() throws Exception {
        // given
        Item savedItem1 = new Item("ItemB", 10000, 10);
        Item savedItem2 = new Item("ItemB", 20000, 20);

        // when
        itemRepository.save(savedItem1);
        itemRepository.save(savedItem2);

        // then
        List<Item> findAll = itemRepository.findAll();
        assertThat(findAll.size()).isEqualTo(2);
        assertThat(findAll).contains(savedItem1, savedItem2);
    }

    @Test
    void testName() throws Exception {
        // given
        Item savedItem = new Item("ItemA", 10000, 10);
        itemRepository.save(savedItem);

        // when
        UpdateItemDto updateItemDto = new UpdateItemDto("ItemB", 20000, 20);
        Long savedItemId = savedItem.getId();
        itemRepository.update(savedItemId, updateItemDto);

        // then
        Item findItem = itemRepository.findById(savedItemId);
        assertThat(findItem.getItemName()).isEqualTo("ItemB");
        assertThat(findItem.getPrice()).isEqualTo(20000);
        assertThat(findItem.getQuantity()).isEqualTo(20);
    }

}