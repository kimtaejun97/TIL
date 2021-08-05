package com.studyweb.studyweb.modules.study.form;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@AllArgsConstructor
@Data
public class UpdatePathForm {
    @NotBlank
    @Pattern(regexp = "([ㄱ-ㅎ가-힣a-z0-9]{2,20}$)")
    private String newPath;
}

