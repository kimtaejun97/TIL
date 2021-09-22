package com.shop.domain;

import org.aspectj.weaver.ast.Or;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.persistence.*;
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

//            order.addOrderItem(orderItem);
            order.getOrderItems().add(orderItem);
//            orderItem.setOrder(order);

            em.flush();
            em.clear();

            Order findOrder = em.find(Order.class, order.getId());

            System.out.println("===================================");
            for(OrderItem o : order.getOrderItems()){
                System.out.println("orderItem = "+  o);
            }
            System.out.println("===================================");



            tx.commit();

        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }

}
