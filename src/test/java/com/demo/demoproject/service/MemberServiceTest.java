package com.demo.demoproject.service;

import com.demo.demoproject.domain.Member;
import com.demo.demoproject.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MemberServiceTest {

    MemoryMemberRepository repository;
    MemberService service;

    @BeforeEach
    void beforeEach() {
        repository = new MemoryMemberRepository();
        service = new MemberService(repository);
    }

    @AfterEach
    void afterEach() {
        repository.clearStore();
    }

    @Test
    void 회원가입() {
        //given
        Member newMember = new Member();
        newMember.setName("spring");

        //when
        Long id = service.join(newMember);

        //then
        Member findMember = service.findOne(id).get();
        assertThat(findMember.getName()).isEqualTo(newMember.getName());
    }

    @Test
    void 중복이름인_경우() {
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when
        service.join(member1);
        IllegalStateException e = Assertions.assertThrows(IllegalStateException.class, () -> {
            service.join(member2);
        });
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}