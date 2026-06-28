package br.com.techchallenge.restaurant.controller;

import br.com.techchallenge.restaurant.domain.dto.RestaurantResponseDTO;
import br.com.techchallenge.restaurant.domain.entity.Restaurant;
import br.com.techchallenge.restaurant.service.RestaurantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/restaurants")
@RequiredArgsConstructor
@Tag(name = "Restaurantes", description = "Endpoints para gestão de restaurantes")
public class RestaurantController {

    private final RestaurantService restaurantService;

    @Operation(summary = "Cadastra um novo restaurante")
    @PostMapping("/{ownerId}")
    public ResponseEntity<RestaurantResponseDTO> save(@RequestBody Restaurant restaurant, @PathVariable Long ownerId) {
        RestaurantResponseDTO newRestaurant = restaurantService.save(restaurant, ownerId);
        return ResponseEntity.status(HttpStatus.CREATED).body(newRestaurant);
    }

    @Operation(summary = "Busca um restaurante por ID")
    @GetMapping("/{id}")
    public ResponseEntity<RestaurantResponseDTO> findById(@PathVariable Long id) {
        RestaurantResponseDTO restaurant = restaurantService.findById(id);
        return ResponseEntity.ok(restaurant);
    }

    @Operation(summary = "Lista todos os restaurantes")
    @GetMapping
    public ResponseEntity<List<RestaurantResponseDTO>> listAll() {
        List<RestaurantResponseDTO> allRestaurants = restaurantService.listAll();
        return ResponseEntity.ok(allRestaurants);
    }

    @Operation(summary = "Atualiza restaurante por ID")
    @PutMapping("/{id}")
    public ResponseEntity<RestaurantResponseDTO> update(@PathVariable Long id, @RequestBody Restaurant restaurant) {
        return ResponseEntity.ok(restaurantService.update(id, restaurant));
    }

    @Operation(summary = "Deleta restaurante por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        restaurantService.delete(id);
        return ResponseEntity.noContent().build();
    }
}