package jpabook.jpashop.domain.item;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("M")
@Getter
@SuperBuilder
public class Movie extends Item {
    private String director;
    private String actor;
}
