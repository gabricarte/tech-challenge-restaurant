package br.com.techchallenge.restaurant.controller;

import br.com.techchallenge.restaurant.domain.dto.response.RestaurantResponseDTO;
import br.com.techchallenge.restaurant.domain.entity.Restaurant;
import br.com.techchallenge.restaurant.mapper.RestaurantMapper;
import br.com.techchallenge.restaurant.service.RestaurantService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private RestaurantMapper restaurantMapper;

    @Operation(summary = "Cria um restaurante")
    @PostMapping("/{ownerId}")
    public ResponseEntity<RestaurantResponseDTO> create(
            @RequestBody Restaurant restaurant,
            @PathVariable Long ownerId) {

        Restaurant createdRestaurant =
                restaurantService.create(restaurant, ownerId);

        return ResponseEntity.status(201)
                .body(restaurantMapper.toDTO(createdRestaurant));
    }

    @Operation(summary = "Encontra um restaurante por ID")
    @GetMapping("/{id}")
    public ResponseEntity<RestaurantResponseDTO> findById(
            @PathVariable Long id) {

        Restaurant restaurant =
                restaurantService.findById(id);

        return ResponseEntity.ok(
                restaurantMapper.toDTO(restaurant)
        );
    }

    @Operation(summary = "Encontra todos restaurantes")
    @GetMapping
    public ResponseEntity<List<RestaurantResponseDTO>> findAll() {

        List<Restaurant> restaurants =
                restaurantService.findAll();

        return ResponseEntity.ok(
                restaurantMapper.toDTOList(restaurants)
        );
    }

    @Operation(summary = "Atualiza um restaurante")
    @PutMapping("/{id}")
    public ResponseEntity<Restaurant> update(
            @PathVariable Long id,
            @RequestBody Restaurant restaurant) {

        return ResponseEntity.ok(
                restaurantService.update(id, restaurant)
        );
    }

    @Operation(summary = "Deleta um restaurante")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id) {

        restaurantService.delete(id);

        return ResponseEntity.noContent().build();
    }
}