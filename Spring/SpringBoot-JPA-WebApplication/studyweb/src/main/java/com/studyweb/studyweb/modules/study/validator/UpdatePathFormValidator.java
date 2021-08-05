package com.studyweb.studyweb.modules.study.validator;

import com.studyweb.studyweb.modules.study.StudyRepository;
import com.studyweb.studyweb.modules.study.form.UpdatePathForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@RequiredArgsConstructor
@Component
public class UpdatePathFormValidator implements Validator {

    private final StudyRepository studyRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.isAssignableFrom(UpdatePathForm.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UpdatePathForm updatePathForm = (UpdatePathForm) o;

        if(studyRepository.existsByPath(updatePathForm.getNewPath())){
            errors.rejectValue("newPath", "invalid.path","이미 존재하는 URL 입니다.");
        }
    }
}
