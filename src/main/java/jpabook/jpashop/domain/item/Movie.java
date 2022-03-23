package jpabook.jpashop.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("M")
@Getter @Setter
public class Movie extends Item {
    private String director;
    private String actor;

    /**
     * 빌더 패턴을 이용한 생성자 매개변수 전달 (이펙티브 자바 [아이템2])
     */
    public static class Builder extends Item.Builder<Movie.Builder> {
        private final String director;
        private final String actor;

        public Builder(String director, String actor) {
            this.director = director;
            this.actor = actor;
        }

        @Override
        public Movie build() {
            return new Movie(this);
        }

        @Override
        protected Movie.Builder self() {
            return this;
        }
    }

    private Movie(Movie.Builder builder) {
        super(builder);
        director = builder.director;
        actor = builder.actor;
    }

}
