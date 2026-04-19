package br.com.techchallenge.restaurant.controller;

import br.com.techchallenge.restaurant.domain.dto.OwnerRequestDTO;
import br.com.techchallenge.restaurant.domain.dto.OwnerResponseDTO;
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

    @PostMapping
    @Operation(summary = "Cadastra um novo proprietário")
    public ResponseEntity<OwnerResponseDTO> save(@RequestBody OwnerRequestDTO dto) {
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

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza os dados de um proprietário")
    public ResponseEntity<OwnerResponseDTO> atualizar(@PathVariable Long id, @RequestBody OwnerRequestDTO dto) {
        return ResponseEntity.ok(ownerService.atualizarDados(id, dto));
    }

    @PatchMapping("/{id}/senha")
    @Operation(summary = "Altera a senha do proprietário")
    public ResponseEntity<Void> trocarSenha(@PathVariable Long id, @RequestBody String novaSenha) {
        ownerService.trocarSenha(id, novaSenha);
        return ResponseEntity.noContent().build();
    }
}