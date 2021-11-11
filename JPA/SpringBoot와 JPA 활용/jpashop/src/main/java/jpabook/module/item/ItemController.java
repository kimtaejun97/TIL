package jpabook.module.item;

import jpabook.module.item.form.BookForm;
import lombok.RequiredArgsConstructor;
import org.dom4j.rule.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/items/new")
    public String createForm(Model model){
        model.addAttribute("form", new BookForm());
        return "items/createItemForm";

    }

    @PostMapping("/items/new")
    public String create(BookForm bookForm){
        itemService.saveItem(bookForm.toEntity());
        return "redirect:/items";
    }

    @GetMapping("/items")
    public String items(Model model){
        List<Item> items = itemService.findItems();
        model.addAttribute("items", items);

        return "items/itemList";
    }

    @GetMapping("/items/{itemId}/edit")
    public String updateItemForm(Model model, @PathVariable Long itemId){
        Book book = (Book)itemService.findById(itemId);
        BookForm bookForm = BookForm.createFormFromEntity(book);
        model.addAttribute("form", bookForm);

        return "items/updateItemForm";
    }

    @PostMapping("/items/{itemId}/edit")
    public String updateItem(@ModelAttribute("form") BookForm bookForm){
        itemService.updateItem(bookForm.getId(), bookForm.toDto());
        return "redirect:/items";
    }
}
