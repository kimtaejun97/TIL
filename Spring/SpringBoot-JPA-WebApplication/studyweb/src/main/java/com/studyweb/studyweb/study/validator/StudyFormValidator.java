package com.studyweb.studyweb.study.validator;

import com.studyweb.studyweb.study.StudyRepository;
import com.studyweb.studyweb.study.form.StudyForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@RequiredArgsConstructor
@Controller
public class StudyFormValidator implements Validator {

    private final StudyRepository studyRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.isAssignableFrom(StudyForm.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        StudyForm studyForm = (StudyForm) o;

        if(studyRepository.existsByPath(studyForm.getPath())){
            errors.rejectValue("path", "invalid.path","이미 존재하는 URL 입니다.");
        }

    }
}
