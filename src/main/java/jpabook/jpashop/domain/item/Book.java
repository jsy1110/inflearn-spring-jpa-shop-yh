package jpabook.jpashop.domain.item;

import lombok.experimental.SuperBuilder;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("B")
@SuperBuilder
public class Book extends Item {
    private String author;
    private String isbn;
}
