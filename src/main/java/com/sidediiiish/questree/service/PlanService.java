package com.sidediiiish.questree.service;

import com.sidediiiish.questree.domain.Member;
import com.sidediiiish.questree.domain.Plan;
import com.sidediiiish.questree.domain.PlanType;
import com.sidediiiish.questree.repository.MemberRepository;
import com.sidediiiish.questree.repository.PlanRepository;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
public class PlanService {
    private final PlanRepository planRepository;
    private final MemberRepository memberRepository;

    public PlanService(PlanRepository planRepository, MemberRepository memberRepository) {
        this.planRepository = planRepository;
        this.memberRepository = memberRepository;
    }

    // plan 작성
    public void create (Plan plan) {planRepository.save(plan);}

    // 전체 plan 조회
    public List<Plan> findAll() {return planRepository.findAll();}

    // plan id로 조회
    public Optional<Plan> findOne(Long planId) {return planRepository.findById(planId);}

    // name으로 조회
    public  List<Plan> findAllByName(String memberName) {
        return planRepository.findAllByMember(memberRepository.findByName(memberName));}

    // 로그인 된 사용자의 plan만 출력
    // 오늘 날짜인 것만 출력(weekly - 요일 해당, routine - 루틴 시작과 종료(현재 term)사이의 날짜가 오늘일 경우)
    public List<Plan> findLoginedAndTodayPlan(String memberName, LocalDate date) {
        Optional<Member> member = memberRepository.findByName(memberName);
        int day = date.getDayOfWeek().getValue();

        // todo
        List<Plan> todoPlans = planRepository.findAllByTypeAndMember(PlanType.TODO, member);

        // weekly
        List<Plan> weeklyPlans = planRepository.findAllByTypeAndMember(PlanType.WEEKLY, member);
        List<Plan> targetedWeeklyPlan = new ArrayList<>();
        for (Plan plan : weeklyPlans) {
            String targetedDay = plan.getWeeklyRoutinePlan().getTargetedDay();
            if (targetedDay.charAt(day) == '1') {
                targetedWeeklyPlan.add(plan);
            }
        }

        // routine
        List<Plan> routinePlans = planRepository.findAllByTypeAndMember(PlanType.ROUTINE, member);

        // 결과 리스트 생성 및 추가
        List<Plan> resultPlans = new ArrayList<>();
        resultPlans.addAll(todoPlans);
        resultPlans.addAll(targetedWeeklyPlan);
        resultPlans.addAll(routinePlans);

        return resultPlans;
    }

    // 수정
    public void update(Long planId, Plan newPlan) {
        Optional<Plan> nowPlan = planRepository.findById(planId);

        // 존재하지 않는 plan id 일 경우
        if (nowPlan.isEmpty()) { throw new IllegalStateException("존재하지 않는 plan id 입니다."); }

        // 내용 갱신
        nowPlan.get().setContent(newPlan.getContent());
        nowPlan.get().setType(newPlan.getType());
        nowPlan.get().setIsContinue(newPlan.getIsContinue());

        planRepository.save(nowPlan.get());
    }

    // 삭제
    public void delete(Long planId) { planRepository.deleteById(planId); }

    public List<Plan> getAllPlans() {
        return planRepository.findAll();
    }
}
