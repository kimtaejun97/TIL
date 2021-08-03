package com.studyweb.studyweb.event;

import com.studyweb.studyweb.domain.Event;
import com.studyweb.studyweb.domain.Study;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface EventRepository extends JpaRepository<Event,Long> {
    @EntityGraph(value = "Event.withEnrollments", type= EntityGraph.EntityGraphType.LOAD)
    List<Event> findByStudyOrderByStartDateTime(Study study);

    @EntityGraph(value = "Event.withEnrollments", type= EntityGraph.EntityGraphType.LOAD)
    Optional<Event> findById(Long eventId);
}