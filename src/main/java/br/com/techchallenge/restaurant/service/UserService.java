package br.com.techchallenge.restaurant.service;

import br.com.techchallenge.restaurant.domain.entity.User;
import br.com.techchallenge.restaurant.exception.InvalidLoginException;
import br.com.techchallenge.restaurant.exception.UserNotFoundException;
import br.com.techchallenge.restaurant.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository usuarioRepository;

    public void validarLogin(String login, String senha) {
        User user = usuarioRepository.findByLogin(login)
                .orElseThrow(() -> new InvalidLoginException());

        if (!user.getPassword().equals(senha)) {
            throw new InvalidLoginException();
        }
    }

    public User atualizarDados(Long id, User novosDados) {
        User user = usuarioRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        user.setName(novosDados.getName());
        user.setEmail(novosDados.getEmail());
        user.setAddress(novosDados.getAddress());

        return usuarioRepository.save(user);
    }

    public void trocarSenha(Long id, String novaSenha) {
        User user = usuarioRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        user.setPassword(novaSenha);
        usuarioRepository.save(user);
    }

    public void deletar(Long id) {
        User user = usuarioRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        usuarioRepository.delete(user);
    }
}