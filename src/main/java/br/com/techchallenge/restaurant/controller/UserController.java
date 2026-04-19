package br.com.techchallenge.restaurant.controller;

import br.com.techchallenge.restaurant.domain.entity.User;
import br.com.techchallenge.restaurant.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PatchMapping("/{id}/dados")
    public ResponseEntity<User> atualizar(@PathVariable Long id, @RequestBody User user) {
        return ResponseEntity.ok(userService.atualizarDados(id, user));
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