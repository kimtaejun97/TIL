package com.studyweb.studyweb.study;

import com.studyweb.studyweb.domain.*;
import com.studyweb.studyweb.event.EventRepository;
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

import java.time.LocalDateTime;
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
    private final EventRepository eventRepository;


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

    public Study getStudy(String path) {
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
        Study study = studyRepository.findStudyWithTagsByPath(path);
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
        Study study = studyRepository.findStudyWithZonesByPath(path);
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

    public Study getStudyToUpdateWithManager(Account account, String path) {
        Study study = studyRepository.findStudyWithManagersByPath(path);

        if(!study.getManagers().contains(account)){
            throw new AccessDeniedException("접근 권한이 없습니다.");
        }
        return study;
    }

    public void studyPublish(Study study) {
        if(study.isPublished() || study.isClosed()){
            throw new IllegalArgumentException(study.getTitle() + " 은 이미 공개중이거나 종료된 스터디 입니다.");
        }

        study.setPublished(true);
        study.setPublishedDateTime(LocalDateTime.now());
    }

    public void studyClose(Study study) {
        if(study.isClosed()){
            throw new IllegalArgumentException(study.getTitle() + "은 이미 종료된 스터디 입니다.");
        }

        study.setPublished(false);
        study.setRecruiting(false);
        study.setClosed(true);
        study.setClosedDateTime(LocalDateTime.now());
    }

    public void studyRemove(Study study) throws IllegalAccessException {
        if(!study.isRemovable()){
            throw new IllegalAccessException("삭제 할 수 없는 스터디 입니다.");
        }

        studyRepository.delete(study);
    }

    public String studyRecruiting(Study study) {

        if(!study.canChangeStateOfRecruiting()){
            return "아직 모집 상태를 변경할 수 없습니다.";
        }
        study.setRecruiting(!study.isRecruiting());
        study.setRecruitingUpdateDateTime(LocalDateTime.now());

        if(study.isRecruiting()){
            return "팀원 모집을 시작 합니다. ";
        }
        else{
            return "팀원 모집을 중단 합니다.";
        }
    }

    public void studyUpdatePath(Study study, String newPath) {
        study.setPath(newPath);
    }

    public void updateStudyTitle(Study study, String newTitle) {
        study.setTitle(newTitle);
    }

    public void joinStudy(Account account, String path) {
        Study study = studyRepository.findStudyWithMembersByPath(path);

        if(isStudyUser(account, study)){
            throw new IllegalArgumentException("이미 가입된 회원 입니다.");
        }

        study.getMembers().add(account);
    }

    public void leaveStudy(Account account, String path) { Study study = studyRepository.findStudyWithTeamsByPath(path);

       if(!isStudyUser(account, study)){
           throw new IllegalArgumentException("가입되지 않은 회원 입니다.");
       }

       study.getManagers().remove(account);
       study.getMembers().remove(account);
    }

    public List<Event> getEventsByStudy(Study study) {
        return eventRepository.findByStudyOrderByStartDateTime(study);
    }

    public Study getStudyToUpdateWithTeam(Account account, String path) throws IllegalAccessException {
        Study study = studyRepository.findStudyWithTeamsByPath(path);

        if(!study.getManagers().contains(account)){
            throw new IllegalAccessException("접근 권한이 없습니다.");
        }

        return study;
    }

    public Study getStudyWithTeam(Account account, String path) {
        Study study = studyRepository.findStudyWithTeamsByPath(path);

        if(!isStudyUser(account, study)){
            throw new IllegalArgumentException("가입되지 않은 회원 입니다.");
        }

        return study;
    }

    private boolean isStudyUser(Account account, Study study){
        if(study.getMembers().contains(account) || study.getManagers().contains(account)){
            return true;
        }
        return false;


    }
}
