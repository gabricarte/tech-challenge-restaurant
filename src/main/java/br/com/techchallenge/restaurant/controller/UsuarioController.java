package br.com.techchallenge.restaurant.controller;

import br.com.techchallenge.restaurant.domain.entity.User;
import br.com.techchallenge.restaurant.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PatchMapping("/{id}/dados")
    public ResponseEntity<User> atualizar(@PathVariable Long id, @RequestBody User user) {
        return ResponseEntity.ok(usuarioService.atualizarDados(id, user));
    }

    @PatchMapping("/{id}/senha")
    public ResponseEntity<Void> trocarSenha(@PathVariable Long id, @RequestBody String novaSenha) {
        usuarioService.trocarSenha(id, novaSenha);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestParam String login, @RequestParam String senha) {
        usuarioService.validarLogin(login, senha);
        return ResponseEntity.ok().build();
    }
}
