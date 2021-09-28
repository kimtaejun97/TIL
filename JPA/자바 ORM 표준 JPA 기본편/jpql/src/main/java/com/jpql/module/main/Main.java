package com.jpql.module.main;

import com.jpql.module.jpql.Member;
import com.jpql.module.jpql.Team;

import javax.persistence.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("h2jpa");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        try {
            Member member = new Member();
            member.setUsername("kim");
            em.persist(member);

            // TypeQuery : 반환 타입이 명확할 때
            // 파라미터 설정 이름 기반.
            TypedQuery<Member> typedQuery = em.createQuery("select m from Member m where m.username=:username", Member.class);
            typedQuery.setParameter("username","kim");
            Member singleResult = typedQuery.getSingleResult();
            System.out.println("singleResult = " + singleResult.getUsername());

            // 파라미터 설정 위치 기반
            List<Member> kim = em.createQuery("select m from Member m where m.username=?1", Member.class)
                    .setParameter(1, "kim")
                    .getResultList();

            // Query
            Query query = em.createQuery("select m.age, m.username from Member m");


            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }
    }
}
