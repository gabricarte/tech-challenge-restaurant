package br.com.techchallenge.restaurant.controller;

import br.com.techchallenge.restaurant.domain.dto.*;
import br.com.techchallenge.restaurant.service.OwnerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/owners")
@Tag(name = "Proprietários", description = "Endpoints para gestão de proprietários")
public class OwnerController {

    private final OwnerService ownerService;

    @PostMapping
    @Operation(summary = "Cadastra um novo proprietário")
    public ResponseEntity<OwnerResponseDTO> save(@Valid @RequestBody OwnerRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ownerService.save(dto));
    }

    @GetMapping
    @Operation(summary = "Lista todos os proprietários")
    public ResponseEntity<List<OwnerResponseDTO>> findAll() {
        return ResponseEntity.ok(ownerService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca um proprietário pelo ID")
    public ResponseEntity<OwnerResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(ownerService.findById(id));
    }

    @GetMapping("/search")
    @Operation(summary = "Busca proprietário pelo nome")
    public ResponseEntity<List<OwnerResponseDTO>> findByName(@Valid @RequestParam String name) {
        List<OwnerResponseDTO> response = ownerService.findByName(name);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza os dados de um proprietário pelo ID")
    public ResponseEntity<OwnerResponseDTO> update(@PathVariable Long id, @Valid @RequestBody OwnerUpdateRequestDTO dto) {
        return ResponseEntity.ok(ownerService.update(id, dto));
    }

    @PatchMapping("/{id}/password")
    @Operation(summary = "Altera a senha do proprietário pelo ID")
    public ResponseEntity<Void> updatePassword(@Valid @PathVariable Long id,
                                               @Valid @RequestBody PasswordUpdateDTO passwordUpdateDTO) {
        ownerService.updatePassword(id, passwordUpdateDTO);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta um proprietário pelo ID")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        ownerService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    @Operation(summary = "Realiza login do proprietário")
    public ResponseEntity<OwnerResponseDTO> login(@Valid @RequestBody UserLoginDTO userLoginDTO) {
        return ResponseEntity.ok(ownerService.login(userLoginDTO));
    }
}