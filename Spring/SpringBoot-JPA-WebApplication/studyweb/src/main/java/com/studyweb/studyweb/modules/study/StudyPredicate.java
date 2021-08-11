package com.studyweb.studyweb.modules.study;

import com.querydsl.core.types.Predicate;
import com.studyweb.studyweb.modules.tags.Tag;
import com.studyweb.studyweb.modules.zone.Zone;
import org.springframework.data.domain.Sort;

import java.util.Set;

public class StudyPredicate {

    public static Predicate findbyKeyword(String keyword) {
        QStudy study = QStudy.study;

        return study.published.isTrue()
                .and(study.zones.any().localNameOfCity.containsIgnoreCase(keyword)
                        .or(study.tags.any().title.containsIgnoreCase(keyword))
                        .or(study.title.containsIgnoreCase(keyword)));
    }


}
