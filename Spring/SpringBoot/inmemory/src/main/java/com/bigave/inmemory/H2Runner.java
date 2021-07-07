package com.bigave.inmemory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Statement;

@Component
public class H2Runner implements ApplicationRunner {

    @Autowired
    DataSource dataSource;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        try(Connection connection = dataSource.getConnection()){
            System.out.println(connection.getMetaData().getUserName());
            System.out.println(connection.getMetaData().getURL());

            Statement statement = connection.createStatement();
            String sql = "CREATE TABLE USER(ID INTEGER  NOT null, name Varchar(255), PRIMARY Key(ID))";
            statement.executeUpdate(sql);
        }

        jdbcTemplate.execute("INSERT into user values (1, 'taejun')");



    }
}
