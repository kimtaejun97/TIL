package com.datajpa.team;

import com.datajpa.member.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class TeamRepository {

    @PersistenceContext
    private EntityManager em;

    public Team save(Team team){
        em.persist(team);
        return team;
    }

    public void delete(Team team){
        em.remove(team);
    }

    public List<Team> findAll() {
        return em.createQuery("select m from Team m", Team.class)
                .getResultList();
    }

    public Optional<Team> findById(Long id){
        Team Team = em.find(Team.class, id);
        return Optional.ofNullable(Team);
    }

    public long count(){
        return em.createQuery("select count(t) from Team t", Long.class)
                .getSingleResult();
    }

    public Team find(Long id){
        return em.find(Team.class, id);
    }
}
