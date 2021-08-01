package com.studyweb.studyweb.study.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@Data
public class UpdateTitleForm {

    @NotBlank
    @Length(max = 50)
    private String newTitle;
}
