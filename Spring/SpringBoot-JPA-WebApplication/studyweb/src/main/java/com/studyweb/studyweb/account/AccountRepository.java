package com.studyweb.studyweb.account;

import com.studyweb.studyweb.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;


@Transactional(readOnly = true)
public interface AccountRepository extends JpaRepository<Account, Long> {

    boolean existsByEmail(String email);

    boolean existsByNickName(String nickName);

    Account findByEmail(String s);

    Account findByNickName(String nickName);

}