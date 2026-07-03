package br.com.techchallenge.restaurant.controller;

import br.com.techchallenge.restaurant.domain.dto.request.CustomerRequestDTO;
import br.com.techchallenge.restaurant.domain.dto.response.CustomerResponseDTO;
import br.com.techchallenge.restaurant.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@Tag(name = "Customers", description = "Endpoints para gestão de Clientes")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Operation(summary = "Salva um cliente")
    @PostMapping
    public ResponseEntity<CustomerResponseDTO> save(@RequestBody CustomerRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.save(dto));
    }

    @Operation(summary = "Encontra todos os clientes")
    @GetMapping
    public ResponseEntity<List<CustomerResponseDTO>> findAll() {
        return ResponseEntity.ok(customerService.findAll());
    }


    @Operation(summary = "Busca um cliente pelo ID")
    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(customerService.findById(id));
    }

    @Operation(summary = "Atualiza os dados de um cliente")
    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponseDTO> atualizar(@PathVariable Long id, @RequestBody CustomerRequestDTO dto) {
        return ResponseEntity.ok(customerService.atualizarDados(id, dto));
    }
}