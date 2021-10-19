package com.super_eddy.jpa_shop.service;

import com.super_eddy.jpa_shop.domain.*;
import com.super_eddy.jpa_shop.domain.item.Book;
import com.super_eddy.jpa_shop.domain.item.Item;
import com.super_eddy.jpa_shop.domain.member.Member;
import com.super_eddy.jpa_shop.domain.order.Order;
import com.super_eddy.jpa_shop.domain.order.OrderRepository;
import com.super_eddy.jpa_shop.domain.order.OrderStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
class OrderServiceTest {

    @PersistenceContext EntityManager em;
    @Autowired OrderService orderService;
    @Autowired
    OrderRepository orderRepository;


    @Test
    void order() {
        //given
        Member member = createMember();
        Item item = createBook("시골 JPA",10000,10);
        int orderCount=2;

        //when
        Long orderId = orderService.order(member.getId(),item.getId(),orderCount);

        //then
        Order getOrder = orderRepository.findOne(orderId);

        assertThat(OrderStatus.ORDER).isEqualTo(getOrder.getStatus());
        assertThat(getOrder.getOrderItems().size()).isEqualTo(1);
        assertThat(item.getStockQuantity()).isEqualTo(8);

    }

    private Member createMember() {
        Member member = new Member();
        member.setName("회원1");
        member.setAddress(new Address("서울","강가","123-123"));
        em.persist(member);
        return member;
    }

    private Book createBook(String name,int price, int stockQuantity){
        Book book = new Book();
        book.setName(name);
        book.setStockQuantity(stockQuantity);
        book.setPrice(price);
        em.persist(book);
        return book;
    }

    @Test
    void cancelOrder() {

        //Given
        Member member = createMember();
        Item item = createBook("시골 JPA",10000,10);
        int orderCount = 2;

        Long orderId = orderService.order(member.getId(),item.getId(),orderCount);

        //when
        orderService.cancelOrder(orderId);

        //Then
        Order getOrder = orderRepository.findOne(orderId);

        assertThat(getOrder.getStatus()).isEqualTo(OrderStatus.CANCEL);
        assertThat(item.getStockQuantity()).isEqualTo(10);

    }
}