package br.com.techchallenge.restaurant.service;

import br.com.techchallenge.restaurant.domain.dto.UserRequestDTO;
import br.com.techchallenge.restaurant.domain.dto.UserResponseDTO;
import br.com.techchallenge.restaurant.domain.entity.Owner;
import br.com.techchallenge.restaurant.domain.entity.User;
import br.com.techchallenge.restaurant.exception.InvalidLoginException;
import br.com.techchallenge.restaurant.exception.UserNotFoundException;
import br.com.techchallenge.restaurant.mapper.UserMapper;
import br.com.techchallenge.restaurant.repository.OwnerRepository;
import br.com.techchallenge.restaurant.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private UserMapper userMapper;

    public void validarLogin(String login, String senha) {
        User user = ownerRepository.findByLogin(login)
                .orElseThrow(() -> new InvalidLoginException());

        if (!user.getPassword().equals(senha)) {
            throw new InvalidLoginException();
        }
    }

    public UserResponseDTO atualizarDados(Long id, UserRequestDTO dto) {

        Owner ownerExistente = ownerRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));


        ownerExistente.setName(dto.name());
        ownerExistente.setEmail(dto.email());
        ownerExistente.setAddress(dto.address());
        Owner atualizado = ownerRepository.save(ownerExistente);

        return userMapper.toDTO(atualizado);
    }

    public void trocarSenha(Long id, String novaSenha) {
        Owner owner = ownerRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        owner.setPassword(novaSenha);

        ownerRepository.save(owner);
    }

    public Owner buscarPorId(Long id) {
        return ownerRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    public void deletar(Long id) {
        Owner owner = ownerRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        ownerRepository.delete(owner);
    }
}