package com.ashokit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashokit.entity.Plan;

public interface PlanRepo extends JpaRepository<Plan, Integer> {

}
