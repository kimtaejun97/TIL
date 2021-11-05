package jpabook.module.order.query;

import com.querydsl.core.QueryFactory;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jpabook.module.delivery.QDelivery;
import jpabook.module.item.QItem;
import jpabook.module.member.QMember;
import jpabook.module.order.QOrder;
import jpabook.module.orderproduct.OrderItem;
import jpabook.module.orderproduct.QOrderItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Repository
public class OrderQueryRepository {

    private final EntityManager em;

    public List<OrderQueryDto> findOrderDto(){
        List<OrderQueryDto> orders = findOrder();

        orders.stream()
                .forEach(o-> o.setOrderItems(findOrderItems(o)));
        return orders;
    }

    private List<OrderItemQueryDto> findOrderItems(OrderQueryDto o) {
        JPAQueryFactory query = new JPAQueryFactory(em);
        QOrderItem orderItem = QOrderItem.orderItem;
        QItem item = QItem.item;

        return query
                .select(Projections.constructor(OrderItemQueryDto.class,
                        orderItem.order.id, item.name, orderItem.orderPrice, orderItem.count))
                .from(orderItem)
                .where(orderItem.order.id.eq(o.getOrderId()))
                .join(orderItem.item, item)
                .fetch();
    }

    public List<OrderQueryDto> findOrder() {
        JPAQueryFactory query = new JPAQueryFactory(em);
        QOrder order = QOrder.order;
        QMember member = QMember.member;
        QDelivery delivery = QDelivery.delivery;

        return query
                .select(Projections.constructor(OrderQueryDto.class,
                        order.id, member.name, order.orderDate, order.status, delivery.address))
                .from(order)
                .join(order.member, member)
                .join(order.delivery, delivery)
                .fetch();


//        return em.createQuery("select new jpabook.module.order.query.OrderQueryDto(o.id, m.name, o.orderDate, o.status, d.address)" +
//                " from Order o" +
//                " join o.member m" +
//                " join o.delivery d", OrderQueryDto.class)
//                .getResultList();
    }

    public List<OrderQueryDto> findOrderDtoOptimization() {
        List<OrderQueryDto> orders = findOrder();

        Map<Long, List<OrderItemQueryDto>> orderItemMap = findOrderItemMap(getOrderIds(orders));
        orders.forEach(o->o.setOrderItems(orderItemMap.get(o.getOrderId())));

        return orders;
    }

    private List<Long> getOrderIds(List<OrderQueryDto> orders) {
        List<Long> orderIds = orders.stream()
                .map(o -> o.getOrderId())
                .collect(Collectors.toList());
        return orderIds;
    }

    private Map<Long, List<OrderItemQueryDto>> findOrderItemMap(List<Long> orderIds) {
        JPAQueryFactory query = new JPAQueryFactory(em);
        QOrderItem orderItem = QOrderItem.orderItem;
        QItem item = QItem.item;

        List<OrderItemQueryDto> orderItems = query
                        .select(Projections.constructor(OrderItemQueryDto.class,
                                orderItem.order.id, item.name, orderItem.orderPrice, orderItem.count))
                        .from(orderItem)
                        .where(orderItem.order.id.in(orderIds))
                        .join(orderItem.item, item)
                        .fetch();

        return DtoToMap(orderItems);
    }

    private Map<Long, List<OrderItemQueryDto>> DtoToMap(List<OrderItemQueryDto> orderItems) {
        Map<Long, List<OrderItemQueryDto>> orderItemMap = orderItems.stream()
                .collect(Collectors.groupingBy(orderItemQueryDto -> orderItemQueryDto.getOrderId()));
        return orderItemMap;
    }
}
