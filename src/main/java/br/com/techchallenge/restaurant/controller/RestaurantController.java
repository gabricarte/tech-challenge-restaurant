package br.com.techchallenge.restaurant.controller;

import br.com.techchallenge.restaurant.domain.dto.RestaurantResponseDTO;
import br.com.techchallenge.restaurant.domain.entity.Restaurant;
import br.com.techchallenge.restaurant.service.RestaurantService;
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

    @PostMapping("/{ownerId}")
    public ResponseEntity<RestaurantResponseDTO> save(@RequestBody Restaurant restaurant, @PathVariable Long ownerId) {
        RestaurantResponseDTO newRestaurant = restaurantService.save(restaurant, ownerId);
        return ResponseEntity.status(HttpStatus.CREATED).body(newRestaurant);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantResponseDTO> findById(@PathVariable Long id) {
        RestaurantResponseDTO restaurant = restaurantService.findById(id);
        return ResponseEntity.ok(restaurant);
    }

    @GetMapping
    public ResponseEntity<List<RestaurantResponseDTO>> listAll() {
        List<RestaurantResponseDTO> allRestaurants = restaurantService.listAll();
        return ResponseEntity.ok(allRestaurants);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestaurantResponseDTO> update(@PathVariable Long id, @RequestBody Restaurant restaurant) {
        return ResponseEntity.ok(restaurantService.update(id, restaurant));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        restaurantService.delete(id);
        return ResponseEntity.noContent().build();
    }
}