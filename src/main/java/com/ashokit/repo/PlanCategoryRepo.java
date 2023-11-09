package com.ashokit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashokit.entity.PlanCategory;

public interface PlanCategoryRepo extends JpaRepository<PlanCategory, Integer> {

}
