package com.studyweb.studyweb.settings;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class Password {

    private String nickName;

    @Length(min=8, max=50)
    private String newPassword;
    private String checkPassword;
}
