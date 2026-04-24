package br.com.techchallenge.restaurant.service;

import br.com.techchallenge.restaurant.domain.entity.Client;
import br.com.techchallenge.restaurant.domain.dto.ClientRequestDTO;
import br.com.techchallenge.restaurant.domain.dto.ClientResponseDTO;
import br.com.techchallenge.restaurant.mapper.ClientMapper;
import br.com.techchallenge.restaurant.repository.ClientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    public ClientService(ClientRepository clientRepository, ClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }

    @Transactional
    public ClientResponseDTO save(ClientRequestDTO dto) {
        Client client = clientMapper.toEntity(dto);
        return clientMapper.toDTO(clientRepository.save(client));
    }

    public List<ClientResponseDTO> findAll() {
        return clientRepository.findAll().stream()
                .map(clientMapper::toDTO)
                .toList();
    }

    public ClientResponseDTO findById(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        return clientMapper.toDTO(client);
    }

    @Transactional
    public ClientResponseDTO atualizarDados(Long id, ClientRequestDTO dto) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        client.setName(dto.name());
        client.setEmail(dto.email());
        client.setBirthDate(dto.birthDate());

        return clientMapper.toDTO(clientRepository.save(client));
    }
}