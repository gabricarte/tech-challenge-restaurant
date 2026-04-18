package br.com.techchallenge.restaurant.service;

import br.com.techchallenge.restaurant.domain.entity.User;
import br.com.techchallenge.restaurant.exception.LoginInvalidoException;
import br.com.techchallenge.restaurant.exception.UsuarioNaoEncontradoException;
import br.com.techchallenge.restaurant.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void validarLogin(String login, String senha) {
        User user = usuarioRepository.findByLogin(login)
                .orElseThrow(() -> new LoginInvalidoException());

        if (!user.getPassword().equals(senha)) {
            throw new LoginInvalidoException();
        }
    }

    public User atualizarDados(Long id, User novosDados) {
        User user = usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNaoEncontradoException(id));

        user.setName(novosDados.getName());
        user.setEmail(novosDados.getEmail());
        user.setAddress(novosDados.getAddress());

        return usuarioRepository.save(user);
    }

    public void trocarSenha(Long id, String novaSenha) {
        User user = usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNaoEncontradoException(id));

        user.setPassword(novaSenha);
        usuarioRepository.save(user);
    }
}