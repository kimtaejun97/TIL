package com.studyweb.studyweb.modules.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;


@Transactional(readOnly = true)
public interface AccountRepository extends JpaRepository<Account, Long> {

    boolean existsByEmail(String email);

    boolean existsByNickName(String nickName);

    Account findByEmail(String s);

    Account findByNickName(String nickName);

}