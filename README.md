# **How to generate Swagger and OpenAPI 3 docs with Spring Boot automatically with SpringDoc**

## 1. **Introduction**

Swagger and OpenAPI 3 are popular tools for documenting REST APIs. Swagger provides an interactive UI for visualizing and interacting with your API documentation, while OpenAPI 3 is a specification for describing your API in a machine-readable format.

Automatic generation of Swagger and OpenAPI 3 docs with **[SpringDoc](https://springdoc.org/)** dependency is a great way to save time and ensure that your documentation is always up-to-date. It is also a good way to improve the quality of your documentation by ensuring that it is consistent and complete.

## 2. **Problem**

Manually documenting a REST API can be a time-consuming and tedious task. It is also difficult to ensure that the documentation is always up-to-date and consistent with the code.

## 3. **Advantages of using Swagger and OpenAPI 3 with springdoc dependency**

Automatic generation of Swagger and OpenAPI 3 docs with [springdoc](https://springdoc.org/) dependency offers a number of advantages for developers, including:

- It can help to ensure that the documentation is always up-to-date and consistent with the code.
- It can save a significant amount of time by doing easier to share the documentation with others.

## 4. **Demo**

To automatically generate Swagger and OpenAPI 3 docs with Spring Boot, you need to add the following dependency to your pom.xml file:

### pom.xml

```xml
<dependency>
      <groupId>org.springdoc</groupId>
      <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
      <version>2.2.0</version>
</dependency>

```

### **application.properties**

You also need to add the following configuration to your Spring Boot application.properties file:

```yaml
springdoc.api-docs.path=/api-docs
springdoc.swagger-ui.path=/swagger-ui
```

This configuration will tell `springdoc-openapi` to generate Swagger and OpenAPI 3 docs at the `/api-docs` and `/swagger-ui` endpoints, respectively.

### **Controller class:**

The controller class is responsible for handling HTTP requests and returning responses. In the Pokemon API example, the `PokemonController` class handles the following requests:

- `GET /pokemons`: Gets all Pokemons
- `GET /pokemons/{id}`: Gets a Pokemon by ID
- `POST /pokemons`: Creates a new Pokemon
- `PUT /pokemons/{id}`: Updates a Pokemon
- `DELETE /pokemons/{id}`: Deletes a Pokemon

```java
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

```

### **Pokemon DTO:**

```java
@Getter
@Setter
@RequiredArgsConstructor
public class Pokemon {

    private Long id;
    private String name;
    private String type;

}

```

### **Service class:**

The service class contains the business logic for the application. In the Pokemon API example, the `PokemonService` class contains the following methods:

- `getAllPokemons()`: Gets all Pokemons
- `getPokemonById(Long id)`: Gets a Pokemon by ID
- `createPokemon(PokemonDTO pokemonDTO)`: Creates a new Pokemon
- `updatePokemon(Long id, PokemonDTO pokemonDTO)`: Updates a Pokemon
- `deletePokemon(Long id)`: Deletes a Pokemon

```java
@Service
public class PokemonService {

    private final List<Pokemon> pokemons = Arrays.asList(
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

```

## 5. **Testing**

### 5.1 Swagger UI

Once you have started your Spring Boot application, you can access the Swagger UI documentation at `http://localhost:8080/swagger-ui`.

You will be able to view the documentation for your Pokemon API, including the CRUD operations.

For example, to get all Pokemons, you would click the "Try it out" button next to the `GET /pokemons` operation. Swagger will send a request to your API and display the list of Pokemons in the UI.

### 5.2 ****OpenAPI Description Path****

You can also try to get the OpenAPI docs by at `http://localhost:8080/api-docs`

This is the result:

```json
{
  "openapi": "3.0.1",
  "info": {
    "title": "OpenAPI definition",
    "version": "v0"
  },
  "servers": [
    {
      "url": "http://localhost:8080",
      "description": "Generated server url"
    }
  ],
  "paths": {
    "/pokemons/{id}": {
      "get": {
        "tags": [
          "pokemon-controller"
        ],
        "operationId": "getPokemonById",
        "parameters": [
          {
            "name": "id",
            "in": "path",
            "required": true,
            "schema": {
              "type": "integer",
              "format": "int64"
            }
          }
        ],
        "responses": {
          "200": {
            "description": "OK",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/Pokemon"
                }
              }
            }
          }
        }
      },
      "put": {
        "tags": [
          "pokemon-controller"
        ],
        "operationId": "updatePokemon",
        "parameters": [
          {
            "name": "id",
            "in": "path",
            "required": true,
            "schema": {
              "type": "integer",
              "format": "int64"
            }
          }
        ],
        "requestBody": {
          "content": {
            "application/json": {
              "schema": {
                "$ref": "#/components/schemas/Pokemon"
              }
            }
          },
          "required": true
        },
        "responses": {
          "200": {
            "description": "OK"
          }
        }
      },
      "delete": {
        "tags": [
          "pokemon-controller"
        ],
        "operationId": "deletePokemon",
        "parameters": [
          {
            "name": "id",
            "in": "path",
            "required": true,
            "schema": {
              "type": "integer",
              "format": "int64"
            }
          }
        ],
        "responses": {
          "200": {
            "description": "OK"
          }
        }
      }
    },
    "/pokemons": {
      "get": {
        "tags": [
          "pokemon-controller"
        ],
        "operationId": "getAllPokemons",
        "responses": {
          "200": {
            "description": "OK",
            "content": {
              "*/*": {
                "schema": {
                  "type": "array",
                  "items": {
                    "$ref": "#/components/schemas/Pokemon"
                  }
                }
              }
            }
          }
        }
      },
      "post": {
        "tags": [
          "pokemon-controller"
        ],
        "operationId": "createPokemon",
        "requestBody": {
          "content": {
            "application/json": {
              "schema": {
                "$ref": "#/components/schemas/Pokemon"
              }
            }
          },
          "required": true
        },
        "responses": {
          "200": {
            "description": "OK"
          }
        }
      }
    }
  },
  "components": {
    "schemas": {
      "Pokemon": {
        "type": "object",
        "properties": {
          "id": {
            "type": "integer",
            "format": "int64"
          },
          "name": {
            "type": "string"
          },
          "type": {
            "type": "string"
          }
        }
      }
    }
  }
}
```

## **Conclusion**

Automatic generation of Swagger and OpenAPI 3 docs is a great way to save time and improve the quality of your API documentation by using [springdoc](https://springdoc.org/) dependency. It is also a good way to make it easier to share your documentation with others.

By using Swagger, you can easily test your API and view the responses. This can help you to ensure that your API is working as expected.

## **Related Articles:**

1. https://howtodoinjava.com/spring-boot/springdoc-openapi-rest-documentation/
2. https://www.baeldung.com/spring-rest-openapi-documentation
3. [github.com/BenHampton/pokiAPI-with-swagger](https://github.com/BenHampton/pokiAPI-with-swagger)