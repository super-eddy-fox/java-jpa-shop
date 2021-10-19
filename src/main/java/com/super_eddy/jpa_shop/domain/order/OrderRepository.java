package com.super_eddy.jpa_shop.domain.order;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static com.super_eddy.jpa_shop.domain.order.QOrder.*;
import static org.springframework.util.ObjectUtils.isEmpty;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public void save(Order order){
        em.persist(order);
    }

    public Order findOne(Long id){
        return em.find(Order.class,id);
    }

    public List<Order> findAll(OrderSearch orderSearch){


        return queryFactory.selectFrom(order)
                .where(usernameEq(orderSearch.getMemberName()),orderStateEq(orderSearch.getOrderStatus()))
                .fetch();
    }

    private BooleanExpression orderStateEq(OrderStatus orderStatus) {
        return isEmpty(orderStatus) ? null: order.status.eq(orderStatus);
    }

    private BooleanExpression usernameEq(String memberName) {
        return isEmpty(memberName) ? null : order.member.name.eq(memberName);
    }
}
