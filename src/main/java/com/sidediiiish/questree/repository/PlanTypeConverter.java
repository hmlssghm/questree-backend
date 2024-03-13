package com.sidediiiish.questree.repository;

import com.sidediiiish.questree.domain.PlanType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class PlanTypeConverter implements AttributeConverter<PlanType, Integer> {
    @Override
    public Integer convertToDatabaseColumn(PlanType attribute){
        return attribute.getValue();
    }

    @Override
    public PlanType convertToEntityAttribute(Integer dbData){
        for (PlanType planType : PlanType.values()){
            if (planType.getValue() == dbData){
                return planType;
            }
        }
        throw new IllegalArgumentException("Invalid PlanType value: " + dbData);
    }

}
