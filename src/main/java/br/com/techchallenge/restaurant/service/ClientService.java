package br.com.techchallenge.restaurant.service;

import br.com.techchallenge.restaurant.domain.entity.Client;
import br.com.techchallenge.restaurant.domain.dto.ClientRequestDTO;
import br.com.techchallenge.restaurant.domain.dto.ClientResponseDTO;
import br.com.techchallenge.restaurant.exception.ClientNotFoundException;
import br.com.techchallenge.restaurant.repository.ClientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Transactional
    public ClientResponseDTO save(ClientRequestDTO dto) {
        Client client = new Client();
        updateEntityFromDTO(client, dto);

        Client savedClient = clientRepository.save(client);
        return toResponseDTO(savedClient);
    }

    public List<ClientResponseDTO> findAll() {
        return clientRepository.findAll().stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    public ClientResponseDTO findById(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException(id));
        return toResponseDTO(client);
    }

    @Transactional
    public ClientResponseDTO atualizarDados(Long id, ClientRequestDTO dto) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException(id));

        client.setName(dto.name());
        client.setEmail(dto.email());
        client.setAddress(dto.address());
        client.setBirthDate(dto.birthDate());

        return toResponseDTO(clientRepository.save(client));
    }

    @Transactional
    public void trocarSenha(Long id, String novaSenha) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException(id));

        client.setPassword(novaSenha);
        clientRepository.save(client);
    }

    private ClientResponseDTO toResponseDTO(Client client) {
        return new ClientResponseDTO(
                client.getId(),
                client.getName(),
                client.getEmail(),
                client.getAddress(),
                client.getBirthDate(),
                client.getLastUpdate()
        );
    }

    private void updateEntityFromDTO(Client client, ClientRequestDTO dto) {
        client.setName(dto.name());
        client.setEmail(dto.email());
        client.setLogin(dto.login());
        client.setPassword(dto.password());
        client.setAddress(dto.address());
        client.setBirthDate(dto.birthDate());
    }
}