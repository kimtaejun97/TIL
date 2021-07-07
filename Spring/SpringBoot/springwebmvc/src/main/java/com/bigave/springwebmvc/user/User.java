package com.bigave.springwebmvc.user;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    String userName;
    String password;

}
