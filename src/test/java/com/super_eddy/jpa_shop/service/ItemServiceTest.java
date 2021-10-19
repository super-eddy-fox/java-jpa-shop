package com.super_eddy.jpa_shop.service;

import com.super_eddy.jpa_shop.domain.item.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;


@SpringBootTest
@Transactional
class ItemServiceTest {

    @Autowired
    ItemService itemService;
    @Autowired
    EntityManager em;

    @Test
    void
    updateItem() {

        Book book = new Book();
        book.setName("book");
        book.setPrice(100);
        book.setAuthor("안성우");

        itemService.saveItem(book);

        em.flush();

        Book book2 = new Book();
        book2.setId(book.getId());
        book2.setPrice(400);

        itemService.saveItem(book2);





        //itemService.updateItem(book.getId(),"new유저",1000);

        //em.persist(book);

        em.flush();





    }
}