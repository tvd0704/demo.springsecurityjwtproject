package com.example.demo.springsecurityjwtproject.repository;

import com.example.demo.springsecurityjwtproject.model.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PokemonRepository extends JpaRepository<Pokemon,Integer> {
    Optional<Pokemon> findByType(String type);
}
