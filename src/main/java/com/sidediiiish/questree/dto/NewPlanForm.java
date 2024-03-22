package com.sidediiiish.questree.dto;

import com.sidediiiish.questree.domain.PlanType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class NewPlanForm {
    private String content;

    private PlanType type;

    // weeklyRoutinePlan
    private String targetedDay;
    private Integer resetDay;

    // countRoutinePlan
    private String startDate;
    private String endDate;
    private Integer intervals;
    private Integer repeatCount;
}
