package jpabook.module.member.form;

import jpabook.module.member.Address;
import jpabook.module.member.Member;
import lombok.Data;
import org.springframework.lang.NonNull;

import javax.validation.constraints.NotEmpty;

@Data
public class MemberForm {

    @NotEmpty(message = "이름을 비울 수 없습니다.")
    private String name;
    private String city;
    private String street;
    private String zipcode;

    public Member toEntity(){
        Member member = createMember();
        return member;
    }

    private Member createMember() {
        Address address = createAddress(city, street, zipcode);

        Member member = new Member();
        member.setName(name);
        member.setAddress(address);

        return member;
    }

    private Address createAddress(String city, String street, String zipcode) {
        return new Address(city, street, zipcode);
    }
}
