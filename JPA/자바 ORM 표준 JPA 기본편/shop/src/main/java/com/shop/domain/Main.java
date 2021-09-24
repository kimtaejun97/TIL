package com.shop.domain;

import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("h2jpa");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();


        tx.begin();
        try {
            Order order = new Order();
            em.persist(order);

            OrderItem orderItem = new OrderItem();
            em.persist(orderItem);

            order.addOrderItem(orderItem);
            em.flush();
            em.clear();

            System.out.println("====================================");
            Order findOrder = em.getReference(Order.class, order.getId());
            System.out.println(emf.getPersistenceUnitUtil().isLoaded(findOrder));
            Hibernate.initialize(findOrder);
            System.out.println(emf.getPersistenceUnitUtil().isLoaded(findOrder));
            findOrder.setStatus(OrderStatus.ORDER);
            Order findOrder2 = em.find(Order.class, order.getId());
            System.out.println("===================================");
            System.out.println("findOrder2 = " + findOrder2.getClass());


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
