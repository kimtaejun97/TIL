package com.studyweb.studyweb.account;

import com.studyweb.studyweb.domain.Account;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class AccountJoinRequestDtoTest {

    @Test
    void createDto(){

        String nickNama = "bigave";
        String email = "kimtaejun@add.add";
        String password = "1234";

        AccountJoinRequestDto accountJoinRequestDto = AccountJoinRequestDto.builder()
                .nickName(nickNama)
                .email(email)
                .password(password)
                .build();

        Account account = accountJoinRequestDto.toEntity();

        assertThat(account.getNickName()).isEqualTo(nickNama);
        assertThat(account.getEmail()).isEqualTo(email);
        assertThat(account.getPassword()).isEqualTo(password);


    }

}