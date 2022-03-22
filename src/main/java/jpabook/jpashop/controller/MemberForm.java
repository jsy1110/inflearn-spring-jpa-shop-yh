package jpabook.jpashop.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

/**
 * Member domain 을 쓰지 않는 것은 실제 domain entity 와 form 의 필드가 다르기 때문이다.
 * domain 을 그대로 쓰려면 맞지 않은 필드에 대한 처리를 어떻게 할지 문제가 된다.
 * 화면처리를 위한 로직이 entity 에 들어가면 entity 가 복잡해진다.
 */
@Getter @Setter
public class MemberForm {

    @NotEmpty(message = "회원 이름은 필수입니다")
    private String name;

    private String city;
    private String street;
    private String zipcode;
}
