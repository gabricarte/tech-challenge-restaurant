package br.com.techchallenge.restaurant.controller;

import br.com.techchallenge.restaurant.domain.entity.Client;
import br.com.techchallenge.restaurant.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
@Tag(name = "Cliente", description = "Endpoints para gestão de Clientes")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    @Operation(summary = "Cadastra um novo cliente")
    public ResponseEntity<Client> save(@RequestBody Client client) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.save(client));
    }

    @GetMapping
    @Operation(summary = "Lista todos os clientes")
    public ResponseEntity<List<Client>> findAll() {
        return ResponseEntity.ok(clientService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca um cliente pelo ID")
    public ResponseEntity<Client> findById(@PathVariable Long id) {
        return ResponseEntity.ok(clientService.findById(id));
    }
}