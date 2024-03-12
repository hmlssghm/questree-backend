package com.sidediiiish.questree.repository;

import com.sidediiiish.questree.domain.Auth;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends CrudRepository<Auth, String> {
    Boolean existsByToken(String token);
}
