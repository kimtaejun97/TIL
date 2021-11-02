package jpabook.module.order;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jpabook.module.delivery.QDelivery;
import jpabook.module.item.QItem;
import jpabook.module.member.QMember;
import jpabook.module.orderproduct.QOrderItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class OrderRepository {

    private final EntityManager em;

    public Order save(Order order) {
        em.persist(order);
        return order;
    }

    public Order findById(Long id) {
        return em.find(Order.class, id);
    }

    public List<Order> findAll(OrderSearch orderSearch){
        JPAQueryFactory query = new JPAQueryFactory(em);

        QOrder order = QOrder.order;
        QMember member = QMember.member;

        return query.selectFrom(order)
                    .join(order.member, member)
                    .where(eqStatus(orderSearch.getOrderStatus(), order),
                            likeName(orderSearch.getMemberName(), order))
                    .limit(1000)
                    .fetch();
    }

    private BooleanExpression likeName(String searchMemberName, QOrder order) {
        if(!StringUtils.hasText(searchMemberName)){
            return null;
        }
        return order.member.name.like(searchMemberName);
    }

    private BooleanExpression eqStatus(OrderStatus searchOrderStatus, QOrder order) {
        if(searchOrderStatus == null){
            return null;
        }
        return order.status.eq(searchOrderStatus);
    }

    public List<Order> findOrdersWithMemberAndDelivery(OrderSearch orderSearch) {
        JPAQueryFactory query = new JPAQueryFactory(em);
        QOrder order = QOrder.order;
        QMember member = QMember.member;
        QDelivery delivery = QDelivery.delivery;

        return query.selectFrom(order)
                        .where(eqStatus(orderSearch.getOrderStatus(), order),
                                likeName(orderSearch.getMemberName(), order))
                        .join(order.member, member).fetchJoin()
                        .join(order.delivery, delivery).fetchJoin()
                        .fetch();
    }

    public List<Order> findOrdersWithMemberAndDeliveryAndItem(OrderSearch orderSearch) {
        JPAQueryFactory query = new JPAQueryFactory(em);
        QOrder order = QOrder.order;
        QMember member = QMember.member;
        QDelivery delivery = QDelivery.delivery;
        QOrderItem orderItem = QOrderItem.orderItem;
        QItem item = QItem.item;

        return query.selectFrom(order)
                .where(eqStatus(orderSearch.getOrderStatus(), order),
                        likeName(orderSearch.getMemberName(), order))
                .join(order.member, member).fetchJoin()
                .join(order.delivery, delivery).fetchJoin()
                .join(order.orderItems, orderItem).fetchJoin()
                .join(orderItem.item, item).fetchJoin()
                .distinct()
                .fetch();
    }
}
