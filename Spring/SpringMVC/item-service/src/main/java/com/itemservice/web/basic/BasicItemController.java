package com.itemservice.web.basic;

import com.itemservice.domain.item.Item;
import com.itemservice.domain.item.ItemRepository;
import com.itemservice.domain.item.dto.UpdateItemDto;
import com.itemservice.domain.item.dto.SaveItemDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/basic/items")
public class BasicItemController {

    private final ItemRepository itemRepository;

    @PostConstruct
    public void init(){
        itemRepository.save(new Item("itemA", 10000, 10));
        itemRepository.save(new Item("itemB", 20000, 20));
    }

    @GetMapping
    public String items(Model model){
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);

        return "basic/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable("itemId") Long itemId, Model model){
        Item item = itemRepository.findById(itemId);
        model.addAttribute(item);

        return "/basic/item";
    }

    @GetMapping("/add")
    public String itemForm(Model model){
        model.addAttribute("itemForm", new SaveItemDto());

        return "/basic/addForm";
    }

    @PostMapping("/add")
    public String addItem(SaveItemDto saveItemDto, RedirectAttributes attributes){
        Item saveItem = itemRepository.save(saveItemDto.toEntity());
        attributes.addAttribute("itemId", saveItem.getId());
//        attributes.addAttribute("isSaved", true);
        attributes.addFlashAttribute("isSaved", true);
//        return "redirect:/basic/items/" + saveItem.getEncodedId();
        return "redirect:/basic/items/{itemId}";
    }


    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model){

        Item item = itemRepository.findById(itemId);
        model.addAttribute(item);

        return "/basic/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String editItem(@PathVariable("itemId") Long itemId ,UpdateItemDto updateItemDto){
        itemRepository.update(itemId,updateItemDto);

        return "redirect:/basic/items/{itemId}"; // 인코딩?
    }
}

