package com.studyweb.studyweb.modules.event;

import com.studyweb.studyweb.modules.account.Account;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    List<Enrollment> findByEvent(Event event);

    List<Enrollment> findByEventAndAccepted(Event event, boolean b);

    boolean existsByEventAndAccount(Event event, Account account);

    @EntityGraph(value = "Enrollment.withEventAndStudy")
    List<Enrollment> findByAccountAndAcceptedOrderByEnrolledAtDesc(Account account, boolean b);
}
