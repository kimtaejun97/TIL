package com.studyweb.studyweb.event;

import com.studyweb.studyweb.domain.Account;
import com.studyweb.studyweb.domain.Enrollment;
import com.studyweb.studyweb.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    List<Enrollment> findByEvent(Event event);

    List<Enrollment> findByEventAndAccepted(Event event, boolean b);

    boolean existsByEventAndAccount(Event event, Account account);
}
