package com.studyweb.studyweb.modules.study;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.JPQLQuery;
import com.studyweb.studyweb.modules.account.Account;
import com.studyweb.studyweb.modules.account.QAccount;
import com.studyweb.studyweb.modules.tags.QTag;
import com.studyweb.studyweb.modules.tags.Tag;
import com.studyweb.studyweb.modules.zone.QZone;
import com.studyweb.studyweb.modules.zone.Zone;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;


@Transactional(readOnly = true)
public class StudyRepositoryExtensionImpl extends QuerydslRepositorySupport implements StudyRepositoryExtension{


    public StudyRepositoryExtensionImpl() {
        super(Study.class);
    }



    @Override
    public Page<Study> findByKeyword(String keyword, Pageable pageable) {
        QStudy study = QStudy.study;
        JPQLQuery<Study> findStudyByKeywordQuery = from(study).where(study.published.isTrue()
                .and(study.title.containsIgnoreCase(keyword))
                .or(study.zones.any().localNameOfCity.containsIgnoreCase(keyword))
                .or(study.tags.any().title.containsIgnoreCase(keyword)))
                .leftJoin(study.tags, QTag.tag).fetchJoin()
                .leftJoin(study.zones, QZone.zone).fetchJoin()
                .leftJoin(study.members, QAccount.account).fetchJoin()
                .distinct();

        JPQLQuery<Study> pageableQuery = getQuerydsl().applyPagination(pageable, findStudyByKeywordQuery);
        QueryResults<Study> fetchResults = pageableQuery.fetchResults();
        System.out.println("=================================: "+ pageable.getPageSize() + "," + pageable.getPageNumber() +","+pageable.getOffset());

        return new PageImpl<>(fetchResults.getResults(), pageable, fetchResults.getTotal());


    }

    @Override
    public List<Study> findFirst9byTagsAndZones(Set<Tag> tags, Set<Zone> zones) {
        QStudy study = QStudy.study;

        return from(study).where(study.published.isTrue()
                .and(study.closed.isFalse())
                .and(study.tags.any().in(tags))
                .or(study.zones.any().in(zones)))
                .leftJoin(study.tags, QTag.tag).fetchJoin()
                .leftJoin(study.zones, QZone.zone).fetchJoin()
                .orderBy(study.publishedDateTime.asc())
                .distinct()
                .limit(9)
                .fetch();

    }

    @Override
    public List<Study> findFirst5IsManager(Account account) {
        QStudy study = QStudy.study;

        return from(study).where(study.managers.contains(account))
                .orderBy(study.publishedDateTime.asc())
                .limit(5)
                .fetch();
    }

    @Override
    public List<Study> findFirst5IsMember(Account account) {
        QStudy study = QStudy.study;

        return from(study).where(study.members.contains(account))
                .orderBy(study.publishedDateTime.asc())
                .limit(5)
                .fetch();
    }

}
