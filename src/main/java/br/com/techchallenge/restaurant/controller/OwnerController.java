package br.com.techchallenge.restaurant.controller;

import br.com.techchallenge.restaurant.domain.dto.request.OwnerRequestDTO;
import br.com.techchallenge.restaurant.domain.dto.response.OwnerResponseDTO;
import br.com.techchallenge.restaurant.service.OwnerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/owners")
@Tag(name = "Dono", description = "Endpoints para gestão de Proprietários")
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @Operation(summary = "Cadastra um novo proprietário")
    @PostMapping
    public ResponseEntity<OwnerResponseDTO> save(@RequestBody OwnerRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ownerService.save(dto));
    }

    @Operation(summary = "Lista todos os proprietários")
    @GetMapping
    public ResponseEntity<List<OwnerResponseDTO>> findAll() {
        return ResponseEntity.ok(ownerService.findAll());
    }

    @Operation(summary = "Busca um proprietário pelo ID")
    @GetMapping("/{id}")
    public ResponseEntity<OwnerResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(ownerService.findById(id));
    }

    @Operation(summary = "Atualiza os dados de um proprietário")
    @PutMapping("/{id}")
    public ResponseEntity<OwnerResponseDTO> update(@PathVariable Long id, @RequestBody OwnerRequestDTO dto) {
        return ResponseEntity.ok(ownerService.updateData(id, dto));
    }

    @Operation(summary = "Altera a senha do proprietário")
    @PatchMapping("/{id}/password")
    public ResponseEntity<Void>changePassword(@PathVariable Long id, @RequestBody String novaSenha) {
        ownerService.changePassword(id, novaSenha);
        return ResponseEntity.noContent().build();
    }
}