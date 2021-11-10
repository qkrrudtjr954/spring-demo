package com.demo.demoproject;

import com.demo.demoproject.repository.MemberRepository;
import com.demo.demoproject.repository.MemoryMemberRepository;
import com.demo.demoproject.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MemberConfig {
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }


}
