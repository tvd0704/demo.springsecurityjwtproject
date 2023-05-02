package com.example.demo.springsecurityjwtproject.service;

import com.example.demo.springsecurityjwtproject.dto.PokemonDto;
import com.example.demo.springsecurityjwtproject.dto.PokemonResponse;
import com.example.demo.springsecurityjwtproject.model.Pokemon;

public interface PokemonService {

    PokemonDto createPokemon(PokemonDto pokemonDto);
    PokemonResponse getAllPokemon(int pageNo, int pageSize);
    PokemonDto getPokemonById(int id);
    PokemonDto updatePokemon(PokemonDto pokemonDto, int id);
    void deletePokemonId(int id);

}
