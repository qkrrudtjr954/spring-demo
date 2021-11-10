package com.demo.demoproject.service;

import com.demo.demoproject.domain.Member;
import com.demo.demoproject.repository.MemberRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {
    private final MemberRepository repository;

    public MemberService(MemberRepository repository) {
        this.repository = repository;
    }

    /**
     * 회원가입
     *
     * @param member 멤버 객체
     * @return 생성된 멤버의 id.
     */
    public Long join(Member member) {
        validateDuplicateMember(member); // 중복회원 검증
        repository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        repository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return repository.findAll();
    }

    /**
     * member 조회
     *
     * @param id 멤버의 id
     * @return 멤버가 존재하면 멤버 인스턴스, 존재하지 않으면 null 을 반환.
     */
    public Optional<Member> findOne(Long id) {
        return repository.findById(id);
    }

}
