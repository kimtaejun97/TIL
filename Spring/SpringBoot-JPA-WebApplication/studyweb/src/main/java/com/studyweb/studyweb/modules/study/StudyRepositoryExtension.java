package com.studyweb.studyweb.modules.study;
import com.studyweb.studyweb.modules.account.Account;
import com.studyweb.studyweb.modules.tags.Tag;
import com.studyweb.studyweb.modules.zone.Zone;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Transactional(readOnly = true)
public interface StudyRepositoryExtension {
    Page<Study> findByKeyword(String keyword, Pageable pageable);

    List<Study> findFirst9byTagsAndZones(Set<Tag> tags, Set<Zone> zones);

    @EntityGraph(attributePaths = {"managers"})
    List<Study> findFirst5IsManager(Account account);

    @EntityGraph(attributePaths = {"members"})
    List<Study> findFirst5IsMember(Account account);
}
