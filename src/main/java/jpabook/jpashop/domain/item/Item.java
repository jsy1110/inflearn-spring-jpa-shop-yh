package jpabook.jpashop.domain.item;

import jpabook.jpashop.domain.Category;
import jpabook.jpashop.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Getter @Setter
public abstract class Item {

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items") // 실무에서는 Many to Many 가 나오는 경우 풀어서 many to one, one to many 로 쪼개자
    private List<Category> categories= new ArrayList<>();

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
     * 빌더 패턴을 이용한 생성자 매개변수 전달 (이펙티브 자바 [아이템2])
     */
    abstract static class Builder<T extends Builder<T>> {
        private Long id;

        private String name;
        private int price;
        private int stockQuantity;

        public T setPrice(int val) {
            price = val;
            return self();
        }

        public T setStockQuantity(int val) {
            stockQuantity = val;
            return self();
        }

        public T setName(String val) {
            name = val;
            return self();
        }
        abstract Item build();

        protected abstract T self();
    }

    Item(Builder<?> builder) {

    }

}
