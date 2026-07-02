package br.com.techchallenge.restaurant.controller;

import br.com.techchallenge.restaurant.domain.entity.UserType;
import br.com.techchallenge.restaurant.service.UserTypeService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user-types")
public class UserTypeController {

    @Autowired
    private UserTypeService userTypeService;

    @Operation(summary = "Cria um tipo de usuário")
    @PostMapping
    public ResponseEntity<UserType> create(@RequestBody UserType userType) {

        return ResponseEntity.status(201).body(userTypeService.create(userType));
    }

    @Operation(summary = "Encontra um tipo de usuário")
    @GetMapping("/{id}")
    public ResponseEntity<UserType> findById(@PathVariable Long id) {

        return ResponseEntity.ok(userTypeService.findById(id));
    }

    @Operation(summary = "Encontra todos os tipos de usuário")
    @GetMapping
    public ResponseEntity<List<UserType>> findAll() {

        return ResponseEntity.ok(userTypeService.findAll()
        );
    }

    @Operation(summary = "Atualiza tipo de usuário")
    @PutMapping("/{id}")
    public ResponseEntity<UserType> update(@PathVariable Long id, @RequestBody UserType userType) {
        return ResponseEntity.ok(userTypeService.update(id, userType)
        );
    }

    @Operation(summary = "Deleta tipo de usuário")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userTypeService.delete(id);

        return ResponseEntity.noContent().build();
    }
}