package com.sidediiiish.questree.domain;

public enum PlanType {
    TODO(0),
    WEEKLY(1),
    COUNT(2);

    private final int value;

    PlanType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
