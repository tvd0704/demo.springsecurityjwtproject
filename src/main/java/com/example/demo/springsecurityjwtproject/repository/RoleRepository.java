package com.example.demo.springsecurityjwtproject.repository;

import com.example.demo.springsecurityjwtproject.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Integer> {
    Optional<Role> findByName(String name);
}
