package br.com.techchallenge.restaurant.service;

import br.com.techchallenge.restaurant.domain.entity.Client;
import br.com.techchallenge.restaurant.domain.dto.ClientRequestDTO;
import br.com.techchallenge.restaurant.domain.dto.ClientResponseDTO;
import br.com.techchallenge.restaurant.exception.ClientNotFoundException;
import br.com.techchallenge.restaurant.mapper.ClientMapper;
import br.com.techchallenge.restaurant.repository.ClientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    public ClientService(ClientRepository clientRepository, ClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }

    public void registrarCliente(ClientRequestDTO dto) {
        Client client = new Client();
        client.setName(dto.name());
        client.setEmail(dto.email());
        client.setLogin(dto.login());
        client.setPassword(dto.password());
        client.setBirthDate(dto.birthDate());

        clientRepository.save(client);
    }

    @Transactional
    public ClientResponseDTO atualizarDados(Long id, ClientRequestDTO dto) {
        Client clientExistente = clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException(id));

        clientExistente.setName(dto.name());
        clientExistente.setEmail(dto.email());
        clientExistente.setAddress(dto.cpf());
        clientExistente.setBirthDate(dto.birthDate());

        Client clienteAtualizado = clientRepository.save(clientExistente);
        return clientMapper.toDTO(clienteAtualizado);
    }

    @Transactional
    public void trocarSenha(Long id, String novaSenha) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException(id));

        client.setPassword(novaSenha);
        clientRepository.save(client);
    }
}