package br.com.techchallenge.restaurant.controller;

import br.com.techchallenge.restaurant.domain.entity.MenuItem;
import br.com.techchallenge.restaurant.service.MenuItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/menu-items")
@Tag(name = "Menu itens", description = "Endpoints para gestão de itens de menu")
public class MenuItemController {

    @Autowired
    private MenuItemService menuItemService;

    @Operation(summary = "Cria um item de menu")
    @PostMapping("/{restaurantId}")
    public ResponseEntity<MenuItem> create(
            @RequestBody MenuItem menuItem,
            @PathVariable Long restaurantId) {

        return ResponseEntity.status(201)
                .body(menuItemService.create(menuItem, restaurantId));
    }

    @Operation(summary = "Encontra um item de menu")
    @GetMapping("/{id}")
    public ResponseEntity<MenuItem> findById(@PathVariable Long id) {

        return ResponseEntity.ok(
                menuItemService.findById(id)
        );
    }

    @Operation(summary = "Encontra todos itens")
    @GetMapping
    public ResponseEntity<List<MenuItem>> findAll() {

        return ResponseEntity.ok(
                menuItemService.findAll()
        );
    }

    @Operation(summary = "Encontra itens por restaurante")
    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<MenuItem>> findByRestaurant(
            @PathVariable Long restaurantId) {

        return ResponseEntity.ok(
                menuItemService.findByRestaurant(restaurantId)
        );
    }

    @Operation(summary = "Atualiza item")
    @PutMapping("/{id}")
    public ResponseEntity<MenuItem> update(
            @PathVariable Long id,
            @RequestBody MenuItem menuItem) {

        return ResponseEntity.ok(
                menuItemService.update(id, menuItem)
        );
    }

    @Operation(summary = "Deleta item")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        menuItemService.delete(id);

        return ResponseEntity.noContent().build();
    }
}