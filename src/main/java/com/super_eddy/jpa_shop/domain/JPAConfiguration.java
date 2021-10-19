package com.super_eddy.jpa_shop.domain;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@RequiredArgsConstructor
@Configuration
public class JPAConfiguration {

    private final EntityManager em;

    @Bean
    JPAQueryFactory jpaQueryFactory() {
        return new JPAQueryFactory(this.em);
    }

}
