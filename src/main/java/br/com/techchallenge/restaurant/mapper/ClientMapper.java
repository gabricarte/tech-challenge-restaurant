package br.com.techchallenge.restaurant.mapper;

import br.com.techchallenge.restaurant.domain.dto.ClientResponseDTO;
import br.com.techchallenge.restaurant.domain.entity.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {

    public ClientResponseDTO toDTO(Client client) {
        return new ClientResponseDTO(
                client.getId(),
                client.getName(),
                client.getEmail(),
                client.getCpf(),
                client.getBirthDate()
        );
    }
}