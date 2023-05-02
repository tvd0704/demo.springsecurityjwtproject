package com.example.demo.springsecurityjwtproject.service.serviceIml;

import com.example.demo.springsecurityjwtproject.dto.PokemonDto;
import com.example.demo.springsecurityjwtproject.dto.PokemonResponse;
import com.example.demo.springsecurityjwtproject.exception.PokemonNotFoundException;
import com.example.demo.springsecurityjwtproject.model.Pokemon;
import com.example.demo.springsecurityjwtproject.repository.PokemonRepository;
import com.example.demo.springsecurityjwtproject.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class PokemonServiceIml implements PokemonService {
    @Autowired
    private PokemonRepository pokemonRepository;

    @Override
    public PokemonDto createPokemon(PokemonDto pokemonDto) {
        Pokemon pokemon = new Pokemon();
        pokemon.setId(pokemonDto.getId());
        pokemon.setName(pokemonDto.getName());
        pokemon.setType(pokemonDto.getType());
        pokemonRepository.save(pokemon);

        PokemonDto pokemonResponse = new PokemonDto();
        pokemonResponse.setId(pokemon.getId());
        pokemonResponse.setName(pokemon.getName());
        pokemonResponse.setType(pokemon.getType());
        return pokemonResponse;
    }

    @Override
    public PokemonResponse getAllPokemon(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo,pageSize);
        Page<Pokemon> pokemons = pokemonRepository.findAll(pageable);
        List<Pokemon> listOfPokemon = pokemons.getContent();
        List<PokemonDto> content = listOfPokemon.stream().map(p -> mapToDto(p)).collect(Collectors.toList());

        PokemonResponse pokemonResponse = new PokemonResponse();
        pokemonResponse.setContent(content);
        pokemonResponse.setPageNo(pokemons.getNumber());
        pokemonResponse.setPageSize(pokemons.getSize());
        pokemonResponse.setTotalElements(pokemons.getTotalElements());
        pokemonResponse.setTotalPages(pokemons.getTotalPages());
        pokemonResponse.setLast(pokemons.isLast());
        return pokemonResponse;

    }

    @Override
    public PokemonDto getPokemonById(int id) {
        Pokemon pokemon = pokemonRepository.findById(id).orElseThrow(()->new PokemonNotFoundException("Pokemon could not be found"));
    return mapToDto(pokemon);
    }

    @Override
    public PokemonDto updatePokemon(PokemonDto pokemonDto, int id) {
        Pokemon pokemon = pokemonRepository.findById(id).orElseThrow(()-> new PokemonNotFoundException("Pokemon could not updated"));
        pokemon.setName(pokemon.getName());
        pokemon.setType(pokemon.getType());
        return mapToDto(pokemonRepository.save(pokemon));
    }

    @Override
    public void deletePokemonId(int id) {
        Pokemon pokemon = pokemonRepository.findById(id).orElseThrow(()->new PokemonNotFoundException("Pokemon could not delete"));
        pokemonRepository.delete(pokemon);
    }

    private PokemonDto mapToDto(Pokemon pokemon) {
        PokemonDto pokemonDto = new PokemonDto();
        pokemonDto.setId(pokemon.getId());
        pokemonDto.setType(pokemon.getType());
        pokemonDto.setName(pokemon.getName());
        return pokemonDto;
    }

    private Pokemon mapToEntity(PokemonDto pokemonDto) {
        Pokemon pokemon = new Pokemon();
        pokemon.setType(pokemonDto.getType());
        pokemon.setName(pokemon.getName());
        return pokemon;
    }

}
