package com.kiran.restapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kiran.restapi.entity.Certificate;
import com.kiran.restapi.entity.College;

public interface CertificateRepository extends JpaRepository<Certificate,Long>{

	 Optional<Certificate> findByTimeAndCollege(String Time, College college);
}
