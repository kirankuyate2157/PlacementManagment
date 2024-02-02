package com.kiran.restapi.repository;

import com.kiran.restapi.entity.College;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CollegeRepository extends JpaRepository<College, Long> {
}
