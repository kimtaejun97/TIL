package com.studyweb.studyweb.account;


import com.studyweb.studyweb.domain.Account;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AccountJoinRequestDto {
    private String nickName;

    private String email;

    private String password;

    public Account toEntity() {
        return Account.builder()
                .nickName(nickName)
                .email(email)
                .password(password)
                .build();
    }
}
