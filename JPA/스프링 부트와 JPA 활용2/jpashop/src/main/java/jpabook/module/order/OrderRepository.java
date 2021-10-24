package jpabook.module.order;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.EnumPath;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jpabook.module.item.Item;
import jpabook.module.member.QMember;
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

        return query
                    .selectFrom(order)
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

}
