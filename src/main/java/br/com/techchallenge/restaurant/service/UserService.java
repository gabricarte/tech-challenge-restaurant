package br.com.techchallenge.restaurant.service;

import br.com.techchallenge.restaurant.domain.entity.Owner;
import br.com.techchallenge.restaurant.domain.entity.User;
import br.com.techchallenge.restaurant.exception.InvalidLoginException;
import br.com.techchallenge.restaurant.exception.UserNotFoundException;
import br.com.techchallenge.restaurant.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private OwnerRepository ownerRepository;

    public void validarLogin(String login, String senha) {
        User user = ownerRepository.findByLogin(login)
                .orElseThrow(() -> new InvalidLoginException());

        if (!user.getPassword().equals(senha)) {
            throw new InvalidLoginException();
        }
    }

    public User atualizarDados(Long id, User novosDados) {
        User user = ownerRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        user.setName(novosDados.getName());
        user.setEmail(novosDados.getEmail());
        user.setAddress(novosDados.getAddress());


        return ownerRepository.save((Owner) user);
    }

    public void trocarSenha(Long id, String novaSenha) {
        User user = ownerRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        user.setPassword(novaSenha);

        ownerRepository.save((Owner) user);
    }

    public void deletar(Long id) {
        User user = ownerRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        ownerRepository.delete((Owner) user);
    }
}