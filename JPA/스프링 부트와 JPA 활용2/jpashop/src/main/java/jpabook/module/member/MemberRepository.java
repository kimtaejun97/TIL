package jpabook.module.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class MemberRepository {

    private final EntityManager em;

    public Member save(Member member){
        em.persist(member);
        return member;
    }

    public Member findById(Long id){
        return em.find(Member.class, id);
    }

    public List<Member> findAll(){
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
    public List<Member> findByName(String name){
        List<Member> name1 = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return name1;
    }

    public boolean existsByName(String name) {
        if(findByName(name).size() != 0){
            return true;
        }
        return false;
    }
}
