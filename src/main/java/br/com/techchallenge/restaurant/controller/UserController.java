package br.com.techchallenge.restaurant.controller;

import br.com.techchallenge.restaurant.domain.dto.UserRequestDTO;
import br.com.techchallenge.restaurant.domain.dto.UserResponseDTO;
import br.com.techchallenge.restaurant.domain.entity.User;
import br.com.techchallenge.restaurant.mapper.UserMapper;
import br.com.techchallenge.restaurant.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> atualizar(@PathVariable Long id, @RequestBody UserRequestDTO dto) {
        UserResponseDTO response = userService.atualizarDados(id, dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar usuário por ID")
    public ResponseEntity<UserResponseDTO> buscarPorId(@PathVariable Long id) {
        User user = userService.buscarPorId(id);
        return ResponseEntity.ok(userMapper.toDTO(user));
    }

    @PatchMapping("/{id}/senha")
    public ResponseEntity<Void> trocarSenha(@PathVariable Long id, @RequestBody String novaSenha) {
        userService.trocarSenha(id, novaSenha);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestParam String login, @RequestParam String senha) {
        userService.validarLogin(login, senha);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        userService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}