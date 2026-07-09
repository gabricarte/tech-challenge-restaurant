package br.com.techchallenge.restaurant.controller;

import br.com.techchallenge.restaurant.domain.dto.request.CustomerUpdateRequestDTO;
import br.com.techchallenge.restaurant.domain.dto.response.CustomerResponseDTO;
import br.com.techchallenge.restaurant.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
@Tag(name = "Customers", description = "Endpoints para gestão de Clientes")
public class CustomerController {

    private final CustomerService customerService;

    @Operation(summary = "Salva um cliente")
    @PostMapping
    public ResponseEntity<CustomerResponseDTO> save(@RequestBody CustomerUpdateRequestDTO dto) {
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
    public ResponseEntity<CustomerResponseDTO> update(@PathVariable Long id, @RequestBody CustomerUpdateRequestDTO dto) {
        return ResponseEntity.ok(customerService.updateData(id, dto));
    }

    @Operation(summary = "Deleta um cliente")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        customerService.delete(id);
        return ResponseEntity.noContent().build();
    }
}