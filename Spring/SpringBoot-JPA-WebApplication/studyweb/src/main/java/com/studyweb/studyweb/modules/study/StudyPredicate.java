package com.studyweb.studyweb.modules.study;

import com.querydsl.core.types.Predicate;

public class StudyPredicate {

    public static Predicate findbyKeyword(String keyword) {
        QStudy study = QStudy.study;

        return study.published.isTrue()
                .and(study.zones.any().localNameOfCity.containsIgnoreCase(keyword)
                        .or(study.tags.any().title.containsIgnoreCase(keyword))
                        .or(study.title.containsIgnoreCase(keyword)));
    }
}
