package jpabook.module.order;

import jpabook.api.OrderSimpleApiController;
import jpabook.module.delivery.Delivery;
import jpabook.module.item.Item;
import jpabook.module.item.ItemRepository;
import jpabook.module.member.Member;
import jpabook.module.member.MemberRepository;
import jpabook.module.orderproduct.OrderItem;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderQueryRepository orderQueryRepository;
    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;

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

    public List<SimpleOrderQueryDto> findOrderDto(OrderSearch orderSearch) {
        return orderQueryRepository.findOrderDto(orderSearch);
    }
}
