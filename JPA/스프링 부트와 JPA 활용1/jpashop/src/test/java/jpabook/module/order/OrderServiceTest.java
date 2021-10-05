package jpabook.module.order;


import jpabook.exception.NotEnoughStockException;
import jpabook.module.item.Book;
import jpabook.module.member.Address;
import jpabook.module.member.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@Transactional
@SpringBootTest
class OrderServiceTest {

    @Autowired
    OrderService orderService;

    @Autowired
    EntityManager em;

    @Autowired
    OrderRepository orderRepository;


    @DisplayName("상품 주문")
    @Test
    public void order() throws Exception {
        // given
        Member member = createMember("kim");
        Book book = createBook("kim", "1235563463", 10);

        // when
        Long orderId = orderService.order(member.getId(), book.getId(), 2);

        // then
        assertThat(orderRepository.findById(orderId).getStatus()).isEqualTo(OrderStatus.ORDER);
        assertThat(orderRepository.findById(orderId).getMember()).isEqualTo(member);
        assertThat(orderRepository.findById(orderId).getOrderItems().get(0).getItem()).isEqualTo(book);
        assertEquals("주문 수량만큼 재고 감소", 8, book.getStockQuantity());
    }

    @DisplayName("")
    @Test
    public void order_notEnoughStock() throws Exception {
        // given
        Member member = createMember("kim");
        Book book = createBook("kim", "1235563463", 10);

        // then
        assertThrows(NotEnoughStockException.class, ()->
                orderService.order(member.getId(), book.getId(), 11));
    }

    @DisplayName("주문 취소")
    @Test
    public void orderCancel () throws Exception {
        // given
        Member member = createMember("kim");
        Book book = createBook("kim", "1235563463", 10);
        Long orderId = orderService.order(member.getId(), book.getId(), 2);

        // when
        orderService.cancelOrder(orderId);

        // then
        assertEquals("오더 상태 취소로 변경", OrderStatus.CANCEL, orderRepository.findById(orderId).getStatus());
        assertEquals("재고 복구", 10, book.getStockQuantity());
    }


    private Member createMember(String name) {
        Member member = new Member();
        member.setName(name);
        member.setAddress(new Address("city", "s", "82"));
        em.persist(member);
        return member;
    }
    private Book createBook(String author, String isbn, int stockQuantity) {
        Book book = new Book();
        book.setAuthor(author);
        book.setIsbn(isbn);
        book.setStockQuantity(stockQuantity);
        em.persist(book);
        return book;
    }
}