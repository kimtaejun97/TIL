package jpabook.module.order.simplequery;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jpabook.module.delivery.QDelivery;
import jpabook.module.member.QMember;
import jpabook.module.order.OrderSearch;
import jpabook.module.order.QOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class SimpleOrderQueryRepository {

    private final EntityManager em;

    public List<SimpleOrderQueryDto> findSimpleOrderDto(OrderSearch orderSearch) {
        JPAQueryFactory query = new JPAQueryFactory(em);
        QOrder order = QOrder.order;
        QMember member = QMember.member;
        QDelivery delivery = QDelivery.delivery;


        return query.select(Projections.constructor(SimpleOrderQueryDto.class,
                order.id,
                member.name,
                order.orderDate,
                order.status,
                delivery.address))
                .from(order)
                .join(order.member, member) // 별칭 클래스.
                .join(order.delivery, delivery)
                .fetch();
        // jpql
//        return em.createQuery(
//                "select new jpabook.module.order.simplequery.SimpleOrderQueryDto(o.id, m.name, o.orderDate, o.status, d.address) " +
//                        "from Order o " +
//                        "join o.member m " +
//                        "join o.delivery d " +
//                        "where o.status = :status and o.member.name = :name", SimpleOrderQueryDto.class)
//                .setParameter("status", orderSearch.getOrderStatus())
//                .setParameter("name", orderSearch.getMemberName())
//                .getResultList();

    }
}
