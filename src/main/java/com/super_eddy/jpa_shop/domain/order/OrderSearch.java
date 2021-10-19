package com.super_eddy.jpa_shop.domain.order;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderSearch {
    private String memberName;
    private OrderStatus orderStatus;

}
