package com.sidediiiish.questree.config;

import com.sidediiiish.questree.repository.MemberRepository;
import com.sidediiiish.questree.repository.PlanRepository;
import com.sidediiiish.questree.service.MemberService;
import com.sidediiiish.questree.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    private final MemberRepository memberRepository;
    private final PlanRepository planRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository, PlanRepository planRepository) {
        this.memberRepository = memberRepository;
        this.planRepository = planRepository;
    }


    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }

    @Bean
    public PlanService planService() { return new PlanService(planRepository); }
}
