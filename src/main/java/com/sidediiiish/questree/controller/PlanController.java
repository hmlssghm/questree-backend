package com.sidediiiish.questree.controller;

import com.sidediiiish.questree.domain.Plan;
import com.sidediiiish.questree.domain.PlanType;
import com.sidediiiish.questree.domain.WeeklyRoutinePlan;
import com.sidediiiish.questree.dto.NewPlanForm;
import com.sidediiiish.questree.dto.UpdatePlanForm;
import com.sidediiiish.questree.repository.MemberRepository;
import com.sidediiiish.questree.service.PlanService;
import com.sidediiiish.questree.service.WeeklyRoutinePlanService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@AllArgsConstructor
@Controller
public class PlanController {
    private final MemberRepository memberRepository;
    private final PlanService planService;
    private final WeeklyRoutinePlanService weeklyRoutinePlanService;
    private final SecretKey secretKey;

    // 일정 조회 페이지(main)
    @GetMapping("/plans")
    public String showPlans(Model model, @CookieValue(name = "token") String token) {
        List<Plan> plans = planService.getAllPlans();

        Claims claims = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
        String memberName = (String) claims.get("name");

        LocalDate currentDate = LocalDate.now();
        String formattedDate = currentDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd (E)"));

        model.addAttribute("plans", plans);
        model.addAttribute("memberName", memberName);
        model.addAttribute("serverDate", formattedDate);
        return "plans/plans";
    }

    // 새로운 일정 등록 페이지
    @GetMapping("/newPlan")
    public String showNewPlanPage(){
        return "plans/newPlan";
    }

    // 새로운 일정 등록
    @RequestMapping(value="/plans/new", method= RequestMethod.POST)
    public String create(@RequestBody NewPlanForm form, @CookieValue(name = "token") String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();

        String memberName = (String) claims.get("name");

        Plan plan = new Plan();
        plan.setContent(form.getContent());
        plan.setType(form.getType());
        plan.setIsContinue(Boolean.TRUE);
        plan.setMember(memberRepository.findByName(memberName).get());
        if (form.getType().equals(PlanType.WEEKLY)){
            WeeklyRoutinePlan weeklyRoutinePlan = new WeeklyRoutinePlan();
            weeklyRoutinePlan.setResetDay(form.getResetDay());
            weeklyRoutinePlan.setTargetedDay(form.getTargetedDay());
            weeklyRoutinePlanService.create(weeklyRoutinePlan);

            plan.setWeeklyRoutinePlan(weeklyRoutinePlan);
        }

        planService.create(plan);
        return "redirect:/plans";
    }

    @PostMapping("/plans/update")
    public String update(@RequestBody UpdatePlanForm form){
        Plan plan = new Plan();
        plan.setContent(form.getContent());
        plan.setType(form.getType());
        plan.setIsContinue(form.getIsContinue());

        planService.update(form.getId(),plan);

        return "redirect:/plans";
    }

    @DeleteMapping("/plans/delete/{id}")
    public String delete(@PathVariable Long id){
        planService.delete(id);
        return "redirect:/plans";
    }
}
