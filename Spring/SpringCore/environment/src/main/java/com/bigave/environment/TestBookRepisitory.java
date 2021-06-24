package com.bigave.environment;

import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository
@Profile("test")
public class TestBookRepisitory implements BookRepository{
}
