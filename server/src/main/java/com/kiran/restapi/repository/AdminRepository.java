package com.kiran.restapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.kiran.restapi.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {
	Optional<Admin> findByNameAndPassword(String name, String password);
}
