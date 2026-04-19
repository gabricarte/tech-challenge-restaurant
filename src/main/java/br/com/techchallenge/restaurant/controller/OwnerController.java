package br.com.techchallenge.restaurant.controller;

import br.com.techchallenge.restaurant.domain.entity.Owner;
import br.com.techchallenge.restaurant.service.OwnerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/donos")
@Tag(name = "Dono", description = "Endpoints para gestão de Proprietários")
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @PostMapping
    @Operation(summary = "Cadastra um novo proprietário")
    public ResponseEntity<Owner> save(@RequestBody Owner owner) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ownerService.save(owner));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca um proprietário pelo ID")
    public ResponseEntity<Owner> findById(@PathVariable Long id) {
        return ResponseEntity.ok(ownerService.findById(id));
    }

}
