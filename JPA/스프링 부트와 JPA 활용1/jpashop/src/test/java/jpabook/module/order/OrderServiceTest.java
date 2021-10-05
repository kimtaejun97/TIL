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

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
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

    @DisplayName("주문 - 재고가 충분하지 않음")
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

    @DisplayName("검색 필터 - 아무것도 없음")
    @Test
    public void search_nonFilter() throws Exception {
        // given
        Member member = createMember("kim");
        Member member2 = createMember("tae");
        Book book = createBook("kim", "1235563463", 10);

        orderService.order(member.getId(), book.getId(), 2);
        Long orderId = orderService.order(member.getId(), book.getId(), 2);
        orderService.order(member2.getId(), book.getId(), 2);
        orderService.cancelOrder(orderId);

        // when
        List<Order> orders = orderService.findOrders(new OrderSearch());

        // then
        assertEquals("주문 건수는 3건", 3, orders.size());
    }

    @DisplayName("검색 필터 - 이름, status = order")
    @Test
    public void search_filter() throws Exception {
        // given
        Member member = createMember("kim");
        Member member2 = createMember("tae");
        Book book = createBook("kim", "1235563463", 10);

        orderService.order(member.getId(), book.getId(), 2);
        Long orderId = orderService.order(member.getId(), book.getId(), 2);
        orderService.order(member2.getId(), book.getId(), 2);
        orderService.cancelOrder(orderId);

        // when
        OrderSearch orderSearch = new OrderSearch();
        orderSearch.setOrderStatus(OrderStatus.ORDER);
        orderSearch.setMemberName("kim");
        List<Order> orders = orderService.findOrders(orderSearch);

        // then
        assertEquals("검색된 주문 건수는 1건", 1, orders.size());
        assertEquals("주문자는 kim", "kim", orders.get(0).getMember().getName());
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