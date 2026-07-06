package br.com.techchallenge.restaurant.controller;


import br.com.techchallenge.restaurant.domain.dto.request.UserRequestDTO;
import br.com.techchallenge.restaurant.domain.dto.response.UserResponseDTO;
import br.com.techchallenge.restaurant.mapper.UserMapper;
import br.com.techchallenge.restaurant.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Tag(name = "Usuários", description = "Endpoints para gestão de usuários")
public class UserController {
    private final UserService userService;

    private final UserMapper userMapper;

    @Operation(summary = "Realiza login de um usuário")
    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody Map<String, String> body) {
        String login = body.get("login");
        String password = body.get("password");

        userService.validarLogin(login, password);

        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Encontra usuário por ID")
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(
                userMapper.toDTO(userService.buscarPorId(id))
        );
    }

    @Operation(summary = "Atualiza dados de usuário")
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> update(@PathVariable Long id, @RequestBody UserRequestDTO dto) {
        return ResponseEntity.ok(
                userService.atualizarDados(id, dto)
        );
    }

    @Operation(summary = "Altera senha de usuário")
    @PatchMapping("/{id}/password")
    public ResponseEntity<Void> changePassword(@PathVariable Long id, @RequestBody Map<String, String> body) {
        String newPassword = body.get("newPassword");
        userService.trocarSenha(id, newPassword);
        return ResponseEntity.noContent().build();
    }
}