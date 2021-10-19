package com.super_eddy.jpa_shop.service;

import com.super_eddy.jpa_shop.domain.member.Member;
import com.super_eddy.jpa_shop.domain.member.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    public void 회원가입() throws Exception{

        //given
        Member member = new Member();
        member.setName("kim");
        //when
        Long saveId = memberService.join(member);
        //then
        assertThat(member).isEqualTo(memberService.findOne(saveId));
    }

    @Test
    public void 중복_회원_예외() throws Exception {
        //given
        Member member1 = new Member();
        member1.setName("Kim");

        Member member2 = new Member();
        member2.setName("Kim");
        //when
        memberService.join(member1);

        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> {
            memberService.join(member2);
        });

        //than
        assertThat(illegalArgumentException).isNotNull();

    }

}