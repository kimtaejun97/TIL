package com.studyweb.studyweb.settings.form;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class Profile {

    @Length(max =35)
    private String bio;

    private String url;
    @Length(max =50)
    private String occupation;

    private String location;

    private String profileImage;

}
