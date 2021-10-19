package com.super_eddy.jpa_shop.service;

import com.super_eddy.jpa_shop.domain.delivery.Delivery;
import com.super_eddy.jpa_shop.domain.delivery.DeliveryStatus;
import com.super_eddy.jpa_shop.domain.item.Item;
import com.super_eddy.jpa_shop.domain.item.ItemRepository;
import com.super_eddy.jpa_shop.domain.member.Member;
import com.super_eddy.jpa_shop.domain.member.MemberRepository;
import com.super_eddy.jpa_shop.domain.order.Order;
import com.super_eddy.jpa_shop.domain.order.OrderItem;
import com.super_eddy.jpa_shop.domain.order.OrderRepository;
import com.super_eddy.jpa_shop.domain.order.OrderSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final MemberRepository memberRepository;
    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;

    @Transactional
    public Long order(Long memberId,Long itemId,int count){

        //엔티티 조회
        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);

        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());
        delivery.setStatus(DeliveryStatus.READY);

        OrderItem orderItem = OrderItem.createOrderItem(item,item.getPrice(),count);
        Order order = Order.createOrder(member,delivery,orderItem);

        orderRepository.save(order);
        return order.getId();
    }

    @Transactional
    public void cancelOrder(Long orderId){
        Order order = orderRepository.findOne(orderId);
        order.cancel();
    }

    public List<Order> findOrders(OrderSearch orderSearch) {
        return this.orderRepository.findAll(orderSearch);
    }
}
