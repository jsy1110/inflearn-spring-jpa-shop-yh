package jpabook.jpashop.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("A")
@Getter @Setter
public class Album extends Item {
    private String artist;
    private String etc;

    /**
     * 빌더 패턴을 이용한 생성자 매개변수 전달 (이펙티브 자바 [아이템2])
     */
    public static class Builder extends Item.Builder<Album.Builder> {
        private final String artist;
        private final String etc;

        public Builder(String artist, String etc) {
            this.artist = artist;
            this.etc = etc;
        }

        @Override
        public Album build() {
            return new Album(this);
        }

        @Override
        protected Album.Builder self() {
            return this;
        }
    }

    private Album(Album.Builder builder) {
        super(builder);
        artist = builder.artist;
        etc = builder.etc;
    }
}
