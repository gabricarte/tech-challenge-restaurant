package br.com.techchallenge.restaurant.controller;

import br.com.techchallenge.restaurant.domain.dto.RestaurantResponseDTO;
import br.com.techchallenge.restaurant.domain.entity.Restaurant;
import br.com.techchallenge.restaurant.mapper.RestaurantMapper;
import br.com.techchallenge.restaurant.service.RestaurantService;
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

    @PostMapping("/{ownerId}")
    public ResponseEntity<RestaurantResponseDTO> cadastrar(@RequestBody Restaurant restaurant, @PathVariable Long ownerId) {
        Restaurant novo = restaurantService.cadastrar(restaurant, ownerId);
        return ResponseEntity.status(201).body(restaurantMapper.toDTO(novo));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantResponseDTO> buscarPorId(@PathVariable Long id) {
        Restaurant restaurant = restaurantService.buscarPorId(id);
        return ResponseEntity.ok(restaurantMapper.toDTO(restaurant));
    }

    @GetMapping
    public ResponseEntity<List<RestaurantResponseDTO>> listarTodos() {
        List<Restaurant> todos = restaurantService.listarTodos();
        return ResponseEntity.ok(restaurantMapper.toDTOList(todos));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Restaurant> atualizar(@PathVariable Long id, @RequestBody Restaurant restaurant) {
        return ResponseEntity.ok(restaurantService.atualizar(id, restaurant));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        restaurantService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}