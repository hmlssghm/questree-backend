package com.sidediiiish.questree.service;

import com.sidediiiish.questree.domain.Plan;
import com.sidediiiish.questree.repository.PlanRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class PlanService {
    private final PlanRepository planRepository;

    public PlanService(PlanRepository planRepository) {
        this.planRepository = planRepository;
    }

    // plan 작성
    public void create (Plan plan) {planRepository.save(plan);}

    // 전체 plan 조회
    public List<Plan> findAll() {return planRepository.findAll();}

    // plan id로 조회
    public Optional<Plan> findOne(Long planId) {return planRepository.findById(planId);}

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

}
