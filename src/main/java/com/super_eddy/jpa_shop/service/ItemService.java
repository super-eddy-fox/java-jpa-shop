package com.super_eddy.jpa_shop.service;

import com.super_eddy.jpa_shop.domain.item.Item;
import com.super_eddy.jpa_shop.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;
    private final EntityManager em;

    @Transactional
    public void saveItem(Item item){
        itemRepository.save(item);
    }

    public List<Item> findItems(){
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId){
        return itemRepository.findOne(itemId);
    }

    @Transactional
    public void updateItem(Long itemId,String itemName,int itemPrice){
        Item item = findOne(itemId);
        item.setName(itemName);
        item.setPrice(itemPrice);
        //em.merge(item);
        itemRepository.save(item);
    }

}
