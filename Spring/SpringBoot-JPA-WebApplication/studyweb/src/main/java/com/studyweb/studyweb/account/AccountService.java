package com.studyweb.studyweb.account;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public String createAcconut(AccountJoinRequestDto accountJoinRequestDto) {
        return accountRepository.save(accountJoinRequestDto.toEntity()).getNickName();
    }
}
