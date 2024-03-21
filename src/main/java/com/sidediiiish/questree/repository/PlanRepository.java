package com.sidediiiish.questree.repository;

import com.sidediiiish.questree.domain.Member;
import com.sidediiiish.questree.domain.Plan;
import com.sidediiiish.questree.domain.PlanType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PlanRepository extends JpaRepository<Plan, Long> {
    List<Plan> findAllByMember(Optional<Member> member);

    List<Plan> findAllByTypeAndMember(PlanType type, Optional<Member> member);
}
