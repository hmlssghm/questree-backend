package com.sidediiiish.questree.repository;

import com.sidediiiish.questree.domain.CountRoutinePlan;
import com.sidediiiish.questree.domain.WeeklyRoutinePlan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountRoutinePlanRepository extends JpaRepository<CountRoutinePlan, Long> {
}
