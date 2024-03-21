package com.sidediiiish.questree.service;

import com.sidediiiish.questree.domain.Plan;
import com.sidediiiish.questree.domain.WeeklyRoutinePlan;
import com.sidediiiish.questree.repository.WeeklyRoutinePlanRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class WeeklyRoutinePlanService {
    private final WeeklyRoutinePlanRepository weeklyRoutinePlanRepository;

    public WeeklyRoutinePlanService(WeeklyRoutinePlanRepository weeklyRoutinePlanRepository) {
        this.weeklyRoutinePlanRepository = weeklyRoutinePlanRepository;
    }

    public void create (WeeklyRoutinePlan plan) { weeklyRoutinePlanRepository.save(plan); }
}
