package com.datajpa.member;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    List<Member> findByUsernameAndAgeGreaterThan(String username, int age);

//    @Query(name = "Member.findByUsername")
    Member findByUsername(@Param("username") String username);

    @Query("select m from Member m where m.username like ?1 and m.age like ?2")
    List<Member> findByUsernameAndAge(String username, int age);
}
