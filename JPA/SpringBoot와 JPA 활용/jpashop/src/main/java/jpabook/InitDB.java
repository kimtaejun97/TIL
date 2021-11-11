package jpabook;

import jpabook.module.delivery.Delivery;
import jpabook.module.item.Book;
import jpabook.module.member.Address;
import jpabook.module.member.Member;
import jpabook.module.order.Order;
import jpabook.module.orderproduct.OrderItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;

@Component
@RequiredArgsConstructor
public class InitDB {
    private final InitService initService;

    @PostConstruct
    public void init(){
        initService.dbInit1();
        initService.dbInit2();
    }

    @Service
    @Transactional
    @RequiredArgsConstructor
    static class InitService{
        private final EntityManager em;
        public void dbInit1(){
            Member member = saveMember("userA", "서울", "12", "12334");
            Book book1 = saveBookItem("JPA", 10000, 100);
            Book book2 = saveBookItem("Spring", 20000, 100);

            OrderItem orderItem1 = OrderItem.createOrderItem(book1, book1.getPrice(), 1);
            OrderItem orderItem2 = OrderItem.createOrderItem(book2, book2.getPrice(), 2);

            Delivery delivery = Delivery.createDelivery(member.getAddress());
            Order order = Order.createOrder(member, delivery, orderItem1, orderItem2);
            em.persist(order);
        }
        public void dbInit2(){
            Member member = saveMember("userB", "광주", "2", "1111");
            Book book1 = saveBookItem("JPA2", 20000, 100);
            Book book2 = saveBookItem("Spring2", 40000, 100);

            OrderItem orderItem1 = OrderItem.createOrderItem(book1, book1.getPrice(), 3);
            OrderItem orderItem2 = OrderItem.createOrderItem(book2, book2.getPrice(), 4);

            Delivery delivery = Delivery.createDelivery(member.getAddress());
            Order order = Order.createOrder(member, delivery, orderItem1, orderItem2);
            em.persist(order);
        }

        private Book saveBookItem(String name ,int price , int stockQuantity) {
            Book book1 = createBookItem(name, price, stockQuantity);
            em.persist(book1);
            return book1;
        }

        private Book createBookItem(String name ,int price , int stockQuantity) {
            Book book1 = new Book();
            book1.setName(name);
            book1.setPrice(price);
            book1.setStockQuantity(stockQuantity);
            return book1;
        }

        private Member saveMember(String userA, String 서울, String s, String s2) {
            Member member = createMember(userA, 서울, s, s2);
            em.persist(member);
            return member;
        }

        private Member createMember(String name, String city, String street, String zipcode) {
            Member member = new Member();
            member.setName(name);
            member.setAddress(new Address(city, street, zipcode));
            return member;
        }
    }
}
