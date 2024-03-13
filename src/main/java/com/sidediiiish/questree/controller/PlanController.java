package com.sidediiiish.questree.controller;

import com.sidediiiish.questree.domain.Plan;
import com.sidediiiish.questree.dto.PlanForm;
import com.sidediiiish.questree.repository.MemberRepository;
import com.sidediiiish.questree.service.PlanService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;

@AllArgsConstructor
@RestController
@Controller
public class PlanController {
    private final MemberRepository memberRepository;
    private final PlanService planService;
    private final SecretKey secretKey;

    // 새로운 일정 등록
    @RequestMapping(value="/plans/new", method= RequestMethod.POST)
    public String create(@RequestBody PlanForm form, @RequestHeader("Authorization") String wrappedToken) {
        String token = wrappedToken.split(" ")[1];

        Claims claims = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();

        String memberName = (String) claims.get("name");

        Plan plan = new Plan();
        plan.setContent(form.getContent());
        plan.setType(form.getType());
        plan.setIsContinue(form.getIsContinue());
        plan.setMember(memberRepository.findByName(memberName).get());

        planService.create(plan);
        return plan.getMember().getName();
    }
}
