package jpabook.jpashop.domain.item;

import jpabook.jpashop.domain.Category;
import jpabook.jpashop.exception.NotEnoughStockException;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Getter
@SuperBuilder
@NoArgsConstructor
public abstract class Item {

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items") // 실무에서는 Many to Many 가 나오는 경우 풀어서 many to one, one to many 로 쪼개자
    @Builder.Default
    private List<Category> categories = new ArrayList<>();

    //==비즈니스 로직==//

    /**
     * stock 증가
     */
    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }

    /**
     * stock 감소
     */
    public void removeStock(int quantity) {
        if(this.stockQuantity - quantity < 0) {
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity -= quantity;
    }

    /**
     * updateItem
     */
    public void updateItem(String name, int price, int stockQuantity) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

}
