package com.studyweb.studyweb.study;

import com.studyweb.studyweb.domain.Account;
import com.studyweb.studyweb.domain.Study;
import com.studyweb.studyweb.domain.Tag;
import com.studyweb.studyweb.domain.Zone;
import com.studyweb.studyweb.settings.form.TagForm;
import com.studyweb.studyweb.settings.form.ZoneForm;
import com.studyweb.studyweb.study.form.StudyDescriptionForm;
import com.studyweb.studyweb.tags.TagRepository;
import com.studyweb.studyweb.zone.ZoneRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@RequiredArgsConstructor
@Service
public class StudyService {
    private final StudyRepository studyRepository;
    private final ModelMapper modelMapper;
    private final TagRepository tagRepository;

    private final ZoneRepository zoneRepository;


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

    public List<String> getStudyTags(Study study) {
        return study.getTags().stream()
                .map(Tag::getTitle)
                .collect(Collectors.toList());
    }

    public Study getStudyToUpdateTags(Account account, String path) {
        Study study = studyRepository.findWithAccountTagsByPath(path);
        if(!study.getManagers().contains(account)){
            throw new AccessDeniedException("접근 권한이 없습니다.");
        }

        return study;
    }

    public void addTag(Study study, TagForm tagForm) {
        String tagTitle = tagForm.getTagTitle();

        Tag tag = tagRepository.findByTitle(tagTitle);
        if(tag == null){
            tagRepository.save(Tag.builder()
                    .title(tagTitle)
                    .build());
        }

        study.getTags().add(tag);
    }

    public ResponseEntity removeTag(Study study, TagForm tagForm) {
        String tagTitle=tagForm.getTagTitle();
        Tag tag = tagRepository.findByTitle(tagTitle);

        if(tag == null){
            return ResponseEntity.badRequest().build();
        }

        study.getTags().remove(tag);

        return ResponseEntity.ok().build();
    }

    public Study getStudyToUpdateZones(Account account, String path) {
        Study study = studyRepository.findWithAccountZonesByPath(path);
        if(!study.getManagers().contains(account)){
            throw new AccessDeniedException("접근 권한이 없습니다.");
        }

        return study;
    }

    public ResponseEntity addZone(Study study, ZoneForm zoneForm) {
        Zone zone = zoneRepository.findByCityAndProvince(zoneForm.getCity(), zoneForm.getProvince());

        if(zone == null){
            return ResponseEntity.badRequest().build();
        }

        study.getZones().add(zone);

        return ResponseEntity.ok().build();
    }

    public ResponseEntity removeZone(Study study, ZoneForm zoneForm){
        Zone zone = zoneRepository.findByCityAndProvince(zoneForm.getCity(), zoneForm.getProvince());

        if(!study.getZones().contains(zone)){
            return ResponseEntity.badRequest().build();
        }

        study.getZones().remove(zone);
        return ResponseEntity.ok().build();

    }

    public List<String> getStudyZones(Study study) {
        return study.getZones().stream()
                .map(Zone::toString)
                .collect(Collectors.toList());
    }
}
