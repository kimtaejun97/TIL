package com.jpql.module.main;

import com.jpql.module.jpql.Member;
import com.jpql.module.jpql.MemberDto;
import com.jpql.module.jpql.Team;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("h2jpa");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        try {
            Team teamA  = new Team();
            teamA.setName("TeamA");
            em.persist(teamA);

            Member member = new Member();
            member.setUsername("kim");
            member.setTeam(teamA);
            em.persist(member);

            Member member2 = new Member();
            member2.setUsername("tae");
            member2.setTeam(teamA);
            em.persist(member2);

            em.flush();
            em.clear();

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
            List<Object[]> resultList = em.createQuery("select m.age, m.username from Member m")
                    .getResultList();
            Object[] result = resultList.get(0);
            System.out.println("age : " + result[0]);
            System.out.println("username : " + result[1]);

            // new 명령어로 조회
            List<MemberDto> results = em.createQuery("select new com.jpql.module.jpql.MemberDto(m.username, m.age) from Member m", MemberDto.class)
                    .getResultList();
            MemberDto memberDto = results.get(0);
            System.out.println("age : " + memberDto.getAge());
            System.out.println("username : " + memberDto.getUsername());

            // 페이징
            for(int i =0; i < 100; i++){
                Member m = new Member();
                m.setUsername("kim" + i);
                m.setAge(i);
                em.persist(m);
            }

            List<Member> pagingResult = em.createQuery("select m from Member m order by m.age desc", Member.class)
                    .setFirstResult(0)
                    .setMaxResults(23)
                    .getResultList();
            for(Member m : pagingResult){
                System.out.println("m = " + m.toString());
            }

            // Inner Join
            Query query = em.createQuery("select m from Member m join m.team t");
            List resultList1 = query.getResultList();
            System.out.println("resultList1 = " + resultList1);

            //left outer join
            List resultList2 = em.createQuery("select m from Member m left join m.team t ")
                    .getResultList();
            System.out.println("resultList2 = " + resultList2);

            // theta join
            List resultList3 = em.createQuery("select m from Member m, Team t where m.username = t.name")
                    .getResultList();
            System.out.println("resultList3 = " + resultList3);


            // 조건식 - case
            List<String> resultList4 = em.createQuery("select " +
                    "case when m.age <=19 then '학생요금' " +
                    "when m.age >=60 then '경로요금' " +
                    "else '일반요금' " +
                    "end " +
                    "from Member m", String.class)
                    .getResultList();

            for(String s : resultList4){
                System.out.println("s = " + s);
            }

            List<String> resultList5 = em.createQuery("select " +
                    "case t.name " +
                    "when 'TeamA' then '110%' " +
                    "when 'TeamB' then '120%' " +
                    "else '100%' " +
                    "end " +
                    "from Team t", String.class)
                    .getResultList();

            // Coalesce
            em.createQuery("select coalesce(m.username, 'unknown') from Member m");

            //NullIf
            em.createQuery("select nullif(m.username, '관리자') from Member m");

            // 사용자 함수
            List<String> resultList6 = em.createQuery("select function('my_concat', m.username) from Member m", String.class)
                    .getResultList();
            System.out.println("resultList6 = " + resultList6);

            //사용자 함수 - hibernate
            List<String> resultList7 = em.createQuery("select my_concat(m.username) from Member m", String.class)
                    .getResultList();
            System.out.println("resultList7 = " + resultList7);

            // join fetch -entity
            em.flush();
            em.clear();

            List<Member> resultList8 = em.createQuery("select m from Member m join fetch m.team", Member.class)
                    .getResultList();
            System.out.println("resultList8 = " + resultList8.get(0));
            System.out.println("resultList8 = " + resultList8.get(0).getTeam().getName());

            // join fetch collection
            List<Team> resultList9 = em.createQuery("select distinct t from Team t join fetch t.members", Team.class)
                    .getResultList();

            for(Team t :resultList9){
                System.out.println("t = " + t.getName());
                System.out.println("size = " + t.getMembers().size());
                for(Member m : t.getMembers()){
                    System.out.println("-> m = " + m);
                }
            }

            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }
    }
}
