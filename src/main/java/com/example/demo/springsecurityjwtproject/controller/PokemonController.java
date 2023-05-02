package com.example.demo.springsecurityjwtproject.controller;

import com.example.demo.springsecurityjwtproject.dto.PokemonDto;
import com.example.demo.springsecurityjwtproject.dto.PokemonResponse;
import com.example.demo.springsecurityjwtproject.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PokemonController {
    @Autowired
    private PokemonService pokemonService;

    @GetMapping("/pokemons")
    public ResponseEntity<PokemonResponse> getPokemons(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize
    ) {
        return new ResponseEntity<>(pokemonService.getAllPokemon(pageNo,pageSize), HttpStatus.OK);
    }
    @GetMapping("/pokemon/{id}")
    public ResponseEntity<PokemonDto> getPokemonDetail(@PathVariable int id) {
        return new ResponseEntity<>(pokemonService.getPokemonById(id),HttpStatus.OK);
    }

    @PostMapping("/pokemon/created")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PokemonDto> createdPokemon(@RequestBody PokemonDto pokemonDto) {
        return new ResponseEntity<>(pokemonService.createPokemon(pokemonDto),HttpStatus.CREATED);
    }

    @PutMapping("/pokemon/{id}/updated")
    public ResponseEntity<PokemonDto> updatedPokemon(@RequestBody PokemonDto pokemonDto, @PathVariable int id) {
        return new ResponseEntity<>(pokemonService.updatePokemon(pokemonDto,id),HttpStatus.OK);
    }

    @DeleteMapping("/pokemon/{id}/updated")
    public ResponseEntity<String> deletePokemon(@PathVariable int id) {
        pokemonService.deletePokemonId(id);
        return new ResponseEntity<>("pokemon delete",HttpStatus.OK);
    }


}
