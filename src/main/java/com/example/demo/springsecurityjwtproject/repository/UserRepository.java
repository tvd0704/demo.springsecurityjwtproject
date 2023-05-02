package com.example.demo.springsecurityjwtproject.repository;

import com.example.demo.springsecurityjwtproject.model.UserEntiy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntiy,Integer> {
    Optional<UserEntiy> findByUsername(String username);
}
