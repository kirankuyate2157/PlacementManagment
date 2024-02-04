package com.kiran.restapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kiran.restapi.entity.Student;

public interface StudentRepository  extends JpaRepository<Student,Long>{

	 Optional<Student> findByRollno(int rollno);
	 Optional<Student> findByHallticket(String hallticket);
	 List<Student> findByYearAndCollege(int year, String college);

}
