package com.demo.demoproject.repository;

import com.demo.demoproject.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);
        Member result = repository.findById(member.getId()).get();
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName(member1.getName()).get();
        assertThat(result).isEqualTo(member1);
        assertThat(result).isNotEqualTo(member2);
    }

    @Test
    public void findAll() {
        for (int i = 1; i <= 10; i++) {
            Member member = new Member();
            member.setName("spring" + i);
            repository.save(member);
        }

        List<Member> members = repository.findAll();

        assertThat(members).hasSize(10);
    }

}
