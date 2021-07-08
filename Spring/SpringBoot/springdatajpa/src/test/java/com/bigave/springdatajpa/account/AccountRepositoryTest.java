package com.bigave.springdatajpa.account;

import org.checkerframework.checker.units.qual.A;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
//@SpringBootTest(properties = "spring.datasource.url=jdbc:h2:mem:testdb")
public class AccountRepositoryTest {
    @Autowired
    DataSource dataSource;
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    AccountRepository accountRepository;

    @Test
    public void di() throws SQLException {
        try(Connection connection = dataSource.getConnection()){
            System.out.println(connection.getMetaData().getURL());
            System.out.println(connection.getMetaData().getDriverName());
        }
        Account account = new Account();
        account.setPassword("123");
        account.setUserName("kim");

        accountRepository.save(account);
        List<Account> results = accountRepository.findByUserName("kim");
        assertThat(results.get(0).getUserName()).isEqualTo("kim");
    }
}