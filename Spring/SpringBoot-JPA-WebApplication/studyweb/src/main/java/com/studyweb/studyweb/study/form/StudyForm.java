package com.studyweb.studyweb.study.form;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class StudyForm {

    @NotBlank
    @Pattern(regexp = "([ㄱ-ㅎ가-힣a-z0-9]{2,20}$)")

    private String path;

    @NotBlank
    @Length(max = 50)
    private String title;

    @NotBlank
    @Length(max = 100)
    private String shortDescription;

    @NotBlank
    private String fullDescription;




}
