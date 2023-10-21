package com.example.waltco.springdocsdemo.service;

import com.example.waltco.springdocsdemo.model.Pokemon;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class PokemonService {

    private final static List<Pokemon> pokemons = Arrays.asList(
            new Pokemon(1L, "Pikachu", "Electric"),
            new Pokemon(2L, "Charmander", "Fire"),
            new Pokemon(3L, "Squirtle", "Water"),
            new Pokemon(3L, "Bulbasaur", "Grass")
    );

    public List<Pokemon> getAllPokemons() {
        return pokemons;
    }

    public Pokemon getPokemonById(Long id) {
        return pokemons.stream()
                .filter(pokemon -> pokemon.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void createPokemon(Pokemon pokemon) {
        pokemons.add(pokemon);
    }

    public void updatePokemon(Long id, Pokemon pokemon) {
        pokemons.stream()
                .filter(pokemon1 -> pokemon1.getId().equals(id))
                .findFirst()
                .ifPresent(pokemon1 -> {
                    pokemon1.setName(pokemon.getName());
                    pokemon1.setType(pokemon.getType());
                });
    }

    public void deletePokemon(Long id) {
        pokemons.removeIf(pokemon -> pokemon.getId().equals(id));
    }
}