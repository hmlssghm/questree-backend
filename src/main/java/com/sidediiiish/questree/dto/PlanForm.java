package com.sidediiiish.questree.dto;

import com.sidediiiish.questree.domain.PlanType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlanForm {
    private String content;

    private PlanType type;
}
