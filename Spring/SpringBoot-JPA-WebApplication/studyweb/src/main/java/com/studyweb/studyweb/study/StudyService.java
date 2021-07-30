package com.studyweb.studyweb.study;

import com.studyweb.studyweb.domain.Account;
import com.studyweb.studyweb.domain.Study;
import com.studyweb.studyweb.study.form.StudyDescriptionForm;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class StudyService {
    private final StudyRepository studyRepository;
    private final ModelMapper modelMapper;


    public Study createNewStudy(Study study, Account account) {
        Study newStudy = studyRepository.save(study);

        newStudy.addManager(account);

        return newStudy;


    }

    public Study getStudyToUpdate(Account account, String path) {
        Study study = getStudy(path);
        if(!study.getManagers().contains(account)){
            throw new AccessDeniedException("해당 기능에 대한 권한이 없습니다.");
        }

        return study;
    }

    private Study getStudy(String path) {
        Study study = studyRepository.findByPath(path);
        if(study == null){
            throw new IllegalArgumentException(path +"에 해당하는 스터디가 존재하지 않습니다.");
        }

        return study;
    }

    public void updateStudyDescription(Study study, StudyDescriptionForm studyDescriptionForm) {
        modelMapper.map(studyDescriptionForm, study);
    }

    public boolean setUsebanner(Study study) {
        study.setUseBanner(!study.isUseBanner());

        return study.isUseBanner();
    }

    public void UpdateBannerImage(Study study, String bannerImage) {
        study.setImage(bannerImage);
    }
}
