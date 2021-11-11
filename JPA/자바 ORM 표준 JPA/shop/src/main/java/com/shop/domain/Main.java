package com.shop.domain;

import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("h2jpa");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();


        tx.begin();
        try {
            Member member = new Member();
            Order order = new Order();
            member.addOrder(order);
            em.persist(member);

            Item book = new Book();
            em.persist(book);

            OrderItem orderItem = new OrderItem();
            orderItem.setItem(book);
            orderItem.setOrder(order);
            em.persist(orderItem);


            em.flush();
            em.clear();

            // 지연 로딩
            System.out.println("====================================");
            Order findOrder = em.getReference(Order.class, order.getId());
            System.out.println(emf.getPersistenceUnitUtil().isLoaded(findOrder));
            Hibernate.initialize(findOrder);
            System.out.println(emf.getPersistenceUnitUtil().isLoaded(findOrder));
            findOrder.setStatus(OrderStatus.ORDER);
            Order findOrder2 = em.find(Order.class, order.getId());
            System.out.println("===================================");
            System.out.println("findOrder2 = " + findOrder2.getClass());


            // 컬렉션 값 타입
            Member member2 = new Member();

            member2.getFavoriteFood().add("치킨");
            member2.getFavoriteFood().add("피자");
            member2.getFavoriteFood().add("족발");
            em.persist(member2);

            em.flush();
            em.clear();

            Member findMember2 = em.find(Member.class, member2.getId());
            findMember2.getFavoriteFood().remove("치킨");
            findMember2.getFavoriteFood().add("새 치킨");

            //JPQL
            List<Member> resultList = em.createQuery("select m from Member m where m.name like %kim%", Member.class)
                    .getResultList();

            //QueryDSL


            tx.commit();

        }catch (Exception e){
            tx.rollback();
            e.printStackTrace();
        }finally {
            em.close();
        }
        emf.close();
    }

}
