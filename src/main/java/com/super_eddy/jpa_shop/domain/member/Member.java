package com.super_eddy.jpa_shop.domain.member;

import com.super_eddy.jpa_shop.domain.Address;
import com.super_eddy.jpa_shop.domain.order.Order;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@Entity
public class Member {

    @Column(name = "member_id")
    @Id @GeneratedValue
    private Long id;
    private String name;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();


}
