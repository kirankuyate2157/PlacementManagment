package com.kiran.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kiran.restapi.entity.Student;

public interface StudentRepository  extends JpaRepository<Student,Integer>{

}
