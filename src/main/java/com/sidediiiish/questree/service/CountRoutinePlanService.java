package com.sidediiiish.questree.service;

import com.sidediiiish.questree.domain.CountRoutinePlan;
import com.sidediiiish.questree.domain.WeeklyRoutinePlan;
import com.sidediiiish.questree.repository.CountRoutinePlanRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class CountRoutinePlanService {
    private final CountRoutinePlanRepository countRoutinePlanRepository;

    public CountRoutinePlanService(CountRoutinePlanRepository countRoutinePlanRepository) {
        this.countRoutinePlanRepository = countRoutinePlanRepository;
    }

    public void create (CountRoutinePlan plan) { countRoutinePlanRepository.save(plan); }
}
