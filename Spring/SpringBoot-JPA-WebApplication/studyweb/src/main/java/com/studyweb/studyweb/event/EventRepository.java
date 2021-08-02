package com.studyweb.studyweb.event;

import com.studyweb.studyweb.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface EventRepository extends JpaRepository<Event,Long> {
}
