package com.shop.domain;

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

            order.addOrderItem(orderItem);

//            em.flush();
//            em.clear();

            System.out.println("===========find order===============");
            Order findOrder = em.find(Order.class, order.getId());
            System.out.println("===================================");

            for(OrderItem o : findOrder.getOrderItems()){
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
