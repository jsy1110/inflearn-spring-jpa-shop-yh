package jpabook.jpashop.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("B")
public class Book extends Item {
    private final String author;
    private final String isbn;


    /**
     * 빌더 패턴을 이용한 생성자 매개변수 전달 (이펙티브 자바 [아이템2])
     */
    public static class Builder extends Item.Builder<Builder> {
        private String author;
        private String isbn;

        public Builder author(String val) {
            author = val;
            return this;
        }

        public Builder isbn(String val) {
            isbn = val;
            return this;
        }

        @Override
        public Book build() {
            return new Book(this);
        }

        @Override
        protected Builder self() {
            return this;
        }
    }

    private Book(Builder builder) {
        super(builder);
        author = builder.author;
        isbn = builder.isbn;
    }
}
