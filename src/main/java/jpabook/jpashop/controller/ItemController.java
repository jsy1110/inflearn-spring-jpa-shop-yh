package jpabook.jpashop.controller;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.service.ItemService;
import jpabook.jpashop.service.UpdateItemDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping(value = "/items/new")
    public String createForm(Model model) {
        model.addAttribute("form", new BookForm());
        return "items/createItemForm";
    }

    @PostMapping(value = "/items/new")
    public String create(BookForm form) {
        /**
         * BookForm 에서 Setter를 없애면 해당 코드는 정상동작 하지 않는다.
         * HTML form에서 넘겨주는 객체 BookForm 의 값이 setter 메서드를 통해 들어오는것으로 보인다.
         * 시간이 될 때 코드를 직접 확인해 볼 것
         */
        Book book = Book.builder()
                .author(form.getAuthor())
                .isbn(form.getIsbn())
                .name(form.getName())
                .price(form.getPrice())
                .stockQuantity(form.getStockQuantity())
                .build();

/**
 * Builder 패턴을 이용하여 위와 같이 변경할 수 있다. (이펙티브 자바 [아이템2])
        book.setName(form.getName());
        book.setStockQuantity(form.getStockQuantity());
        book.setPrice(form.getPrice());
        book.setAuthor(form.getAuthor());
        book.setIsbn(form.getIsbn());
*/
        itemService.saveItem(book);
        return "redirect:/";
    }

    @GetMapping(value = "/items")
    public String list(Model model) {
        List<Item> items = itemService.findItems();
        model.addAttribute("items", items);
        return "items/itemList";
    }

    @GetMapping(value = "/items/{itemId}/edit")
    public String updateItemForm(@PathVariable("itemId") Long itemId, Model model) {
        Book item = (Book) itemService.findOne(itemId);

        BookForm form = BookForm.builder()
                .id(item.getId())
                .name(item.getName())
                .price(item.getPrice())
                .stockQuantity(item.getStockQuantity())
                .author(item.getAuthor())
                .isbn(item.getIsbn())
                .build();

        model.addAttribute("form", form);
        return "Items/updateItemForm";

    }


    @PostMapping(value = "/items/{itemId}/edit")
    public String updateItem(@PathVariable("itemId") Long itemId, @ModelAttribute("form") BookForm form) {
        /**아래와 같이 사용하면 book 객체가 영속성이 없기 때문에 '변경 감지'가 일어나지 않는다.
         * 그렇기 때문에 saveItem 메서드를 통해 merge 로 빠지게 되는데, 실무에서 merge 는 쓰지 않는것이 좋다.
         * merge는 엔티티 전체 속성에 대해 update하기 때문에 자칫하다간 의도치 않게 null 로 update 되기 때문이다.
        Book book = Book.builder()
                .id(form.getId())
                .name(form.getName())
                .price(form.getPrice())
                .stockQuantity(form.getStockQuantity())
                .author(form.getAuthor())
                .isbn(form.getIsbn())
                .build();

        itemService.saveItem(book);
        */
        UpdateItemDto itemDto = new UpdateItemDto();

        itemDto.setName(form.getName());
        itemDto.setPrice(form.getPrice());
        itemDto.setStockQuantity(form.getStockQuantity());
        itemService.updateItem(itemId, itemDto);

        return "redirect:/items";
    }
}
