package com.kiran.restapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kiran.restapi.entity.College;

public interface CollegeRepository extends JpaRepository<College,Long>{
	
	  @Query("SELECT c FROM College c WHERE c.college_name = :collegeName AND c.location = :location")
	    Optional<College> findByCollegeNameAndLocation(String collegeName, String location);
}
