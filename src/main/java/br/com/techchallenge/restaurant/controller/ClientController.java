package br.com.techchallenge.restaurant.controller;

import br.com.techchallenge.restaurant.domain.dto.ClientRequestDTO;
import br.com.techchallenge.restaurant.domain.dto.ClientResponseDTO;
import br.com.techchallenge.restaurant.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/clientes")
@Tag(name = "Cliente", description = "Endpoints para gestão de Clientes")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    @Operation(summary = "Cadastra um novo cliente")
    public ResponseEntity<ClientResponseDTO> save(@RequestBody ClientRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.save(dto));
    }

    @GetMapping
    @Operation(summary = "Lista todos os clientes")
    public ResponseEntity<List<ClientResponseDTO>> findAll() {
        return ResponseEntity.ok(clientService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca um cliente pelo ID")
    public ResponseEntity<ClientResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(clientService.findById(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza os dados de um cliente")
    public ResponseEntity<ClientResponseDTO> atualizar(@PathVariable Long id, @RequestBody ClientRequestDTO dto) {
        return ResponseEntity.ok(clientService.atualizarDados(id, dto));
    }

    @PatchMapping("/{id}/senha")
    @Operation(summary = "Altera a senha do cliente")
    public ResponseEntity<Void> trocarSenha(@PathVariable Long id, @RequestBody String novaSenha) {
        clientService.trocarSenha(id, novaSenha);
        return ResponseEntity.noContent().build();
    }
}