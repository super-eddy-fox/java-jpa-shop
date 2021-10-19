package com.super_eddy.jpa_shop.domin;

import com.super_eddy.jpa_shop.domain.member.Member;
import com.super_eddy.jpa_shop.domain.member.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Transactional
    @Test
    @Rollback(false)
    public void testMember(){
        Member member = new Member();
        member.setName("memberA");

        Long savedId = memberRepository.save(member);
        Member findMember = memberRepository.findOne(savedId);

        assertThat(findMember.getId()).isEqualTo(member.getId());
        assertThat(findMember.getName()).isEqualTo(member.getName());

    }

}