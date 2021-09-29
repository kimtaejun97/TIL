package com.jpql.dialect;

import org.hibernate.dialect.H2Dialect;
import org.hibernate.dialect.function.VarArgsSQLFunction;
import org.hibernate.type.StandardBasicTypes;


public class MyH2Dialect extends H2Dialect {

    public MyH2Dialect(){
        registerFunction("my_concat", new VarArgsSQLFunction(StandardBasicTypes.STRING, "(", "||", ")"));
    }
}
