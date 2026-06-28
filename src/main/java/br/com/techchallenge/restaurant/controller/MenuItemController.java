package br.com.techchallenge.restaurant.controller;

import br.com.techchallenge.restaurant.domain.entity.MenuItem;
import br.com.techchallenge.restaurant.service.MenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/menu-items")
public class MenuItemController {

    @Autowired
    private MenuItemService menuItemService;

    @PostMapping("/{restaurantId}")
    public ResponseEntity<MenuItem> create(
            @RequestBody MenuItem menuItem,
            @PathVariable Long restaurantId) {

        return ResponseEntity.status(201)
                .body(menuItemService.create(menuItem, restaurantId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuItem> findById(@PathVariable Long id) {

        return ResponseEntity.ok(
                menuItemService.findById(id)
        );
    }

    @GetMapping
    public ResponseEntity<List<MenuItem>> findAll() {

        return ResponseEntity.ok(
                menuItemService.findAll()
        );
    }

    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<MenuItem>> findByRestaurant(
            @PathVariable Long restaurantId) {

        return ResponseEntity.ok(
                menuItemService.findByRestaurant(restaurantId)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<MenuItem> update(
            @PathVariable Long id,
            @RequestBody MenuItem menuItem) {

        return ResponseEntity.ok(
                menuItemService.update(id, menuItem)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        menuItemService.delete(id);

        return ResponseEntity.noContent().build();
    }
}