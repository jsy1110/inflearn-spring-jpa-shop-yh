package jpabook.jpashop.controller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

@Getter @Setter
public class BookForm {

    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    private String author;
    private String isbn;
/*
    public static class Builder {
        private final Long id;

        private String name = "";
        private int price = 0;
        private int stockQuantity = 0;

        private String author = "";
        private String isbn = "";

        public Builder(Long id) {
            this.id = id;
        }

        public Builder name(String val) {
            name = val;
            return this;
        }
        public Builder price(int val) {
            price = val;
            return this;
        }
        public Builder stockQuantity(int val) {
            stockQuantity = val;
            return this;
        }
        public Builder author(String val) {
            author = val;
            return this;
        }
        public Builder isbn(String val) {
            isbn = val;
            return this;
        }

        public BookForm build() {
            return new BookForm(this);
        }
    }

    private BookForm(Builder builder) {
        id = builder.id;
        name = builder.name;
        price = builder.price;
        stockQuantity = builder.stockQuantity;
        author = builder.author;
        isbn = builder.isbn;
    }
*/
}
