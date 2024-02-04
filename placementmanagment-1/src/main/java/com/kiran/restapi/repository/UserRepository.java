package com.kiran.restapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kiran.restapi.entity.User;


public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByNameAndPassword(String name, String password);
    Optional<User> findByName(String name);
}
