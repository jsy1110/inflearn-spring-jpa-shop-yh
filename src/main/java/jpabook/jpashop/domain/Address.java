package jpabook.jpashop.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Getter
@Embeddable
public class Address {

    private String city;
    private String street;
    private String zipcode;

    // 값 타입은 변경 불가능해야 하기 때문에 setter 빼고 생성자로 초기화
    // but, JPA 스펙상 기본 생성자를 만들어야 하므로 (리플렉션, 프록시 등의 기술 지원을 위함) protected 껍데기를 만들어 둔다.
    protected Address() {
    }

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
