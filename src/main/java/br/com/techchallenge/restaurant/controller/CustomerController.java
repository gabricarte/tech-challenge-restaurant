package br.com.techchallenge.restaurant.controller;

import br.com.techchallenge.restaurant.domain.dto.*;
import br.com.techchallenge.restaurant.service.CustomerService;
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
@RequestMapping("/api/v1/customers")
@Tag(name = "Clientes", description = "Endpoints para gestão de clientes")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    @Operation(summary = "Cadastra um novo cliente")
    public ResponseEntity<CustomerResponseDTO> save(@Valid @RequestBody CustomerRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.save(dto));
    }

    @GetMapping
    @Operation(summary = "Lista todos clientes")
    public ResponseEntity<List<CustomerResponseDTO>> findAll() {
        return ResponseEntity.ok(customerService.findAll());
    }


    @GetMapping("/{id}")
    @Operation(summary = "Busca um cliente pelo ID")
    public ResponseEntity<CustomerResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(customerService.findById(id));
    }

    @GetMapping("/search")
    @Operation(summary = "Busca cliente pelo nome")
    public ResponseEntity<List<CustomerResponseDTO>> findByName(@Valid @RequestParam String name) {
        List<CustomerResponseDTO> response = customerService.findByName(name);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza os dados de um cliente pelo ID")
    public ResponseEntity<CustomerResponseDTO> update(@PathVariable Long id, @Valid @RequestBody CustomerUpdateRequestDTO dto) {
        return ResponseEntity.ok(customerService.update(id, dto));
    }

    @PatchMapping("/{id}/password")
    @Operation(summary = "Altera a senha do cliente pelo ID")
    public ResponseEntity<Void> updatePassword(@Valid @PathVariable Long id,
                                               @Valid @RequestBody PasswordUpdateDTO passwordUpdateDTO) {
        customerService.updatePassword(id, passwordUpdateDTO);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta um cliente pelo ID")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        customerService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    @Operation(summary = "Realiza login do cliente")
    public ResponseEntity<CustomerResponseDTO> login(@Valid @RequestBody UserLoginDTO userLoginDTO) {
        return ResponseEntity.ok(customerService.login(userLoginDTO));
    }
}