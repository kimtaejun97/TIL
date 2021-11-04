package jpabook.module.order;

import jpabook.module.delivery.Delivery;
import jpabook.module.item.Item;
import jpabook.module.item.ItemRepository;
import jpabook.module.member.Member;
import jpabook.module.member.MemberRepository;
import jpabook.module.order.query.OrderQueryDto;
import jpabook.module.order.query.OrderQueryRepository;
import jpabook.module.order.simplequery.SimpleOrderQueryRepository;
import jpabook.module.order.simplequery.SimpleOrderQueryDto;
import jpabook.module.orderproduct.OrderItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final SimpleOrderQueryRepository simpleOrderQueryRepository;
    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;
    private final OrderQueryRepository orderQueryRepository;

    @Transactional
    public Long order(Long memberId, Long itemId, int count){
        Member member = memberRepository.findById(memberId);
        Item item = itemRepository.findById(itemId);

        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);
        Order order = createOrder(member, orderItem);
        orderRepository.save(order);

        return order.getId();
    }

    private Order createOrder(Member member, OrderItem orderItem) {
        Delivery delivery = Delivery.createDelivery(member.getAddress());
        Order order = Order.createOrder(member, delivery, orderItem);

        return order;
    }

    @Transactional
    public void cancelOrder(Long orderId){
        Order order = orderRepository.findById(orderId);
        order.cancel();
    }

    public List<Order> findOrders(OrderSearch orderSearch){
        return orderRepository.findAll(orderSearch);
    }


    public List<Order> findOrdersWithMemberDelivery(OrderSearch orderSearch) {
        return orderRepository.findOrdersWithMemberAndDelivery(orderSearch);
    }

    public List<SimpleOrderQueryDto> findSimpleOrderDto(OrderSearch orderSearch) {
        return simpleOrderQueryRepository.findSimpleOrderDto(orderSearch);
    }

    public List<Order> findOrdersWithMemberDeliveryItem(OrderSearch orderSearch) {
        return orderRepository.findOrdersWithMemberAndDeliveryAndItem(orderSearch);
    }

    public List<OrderQueryDto> findOrderDto(OrderSearch orderSearch) {
        return orderQueryRepository.findOrder(orderSearch);

    }
}
