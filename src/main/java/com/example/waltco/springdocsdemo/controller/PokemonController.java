package com.example.waltco.springdocsdemo.controller;

import com.example.waltco.springdocsdemo.model.Pokemon;
import com.example.waltco.springdocsdemo.service.PokemonService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pokemons")
public class PokemonController {

    private final PokemonService pokemonService;

    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @GetMapping
    public List<Pokemon> getAllPokemons() {
        return pokemonService.getAllPokemons();
    }

    @GetMapping("/{id}")
    public Pokemon getPokemonById(@PathVariable Long id) {
        return pokemonService.getPokemonById(id);
    }

    @PostMapping
    public void createPokemon(@RequestBody Pokemon pokemon) {
        pokemonService.createPokemon(pokemon);
    }

    @PutMapping("/{id}")
    public void updatePokemon(@PathVariable Long id, @RequestBody Pokemon pokemon) {
        pokemonService.updatePokemon(id, pokemon);
    }

    @DeleteMapping("/{id}")
    public void deletePokemon(@PathVariable Long id) {
        pokemonService.deletePokemon(id);
    }
}
