package com.studyweb.studyweb.study;

import com.studyweb.studyweb.domain.Account;
import com.studyweb.studyweb.domain.Study;
import com.studyweb.studyweb.domain.Zone;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface StudyRepository extends JpaRepository<Study, Long> {
    boolean existsByPath(String path);

    @EntityGraph(value="Study.withAll", type = EntityGraph.EntityGraphType.LOAD)
    Study findByPath(String path);

    @EntityGraph(value = "Study.withAccountTags", type = EntityGraph.EntityGraphType.FETCH)
    Study findWithAccountTagsByPath(String path);

    @EntityGraph(value = "Study.withAccountZones", type = EntityGraph.EntityGraphType.FETCH)
    Study findWithAccountZonesByPath(String path);

}
