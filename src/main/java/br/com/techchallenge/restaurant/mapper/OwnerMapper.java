package br.com.techchallenge.restaurant.mapper;

import br.com.techchallenge.restaurant.domain.dto.OwnerResponseDTO;
import br.com.techchallenge.restaurant.domain.entity.Owner;
import org.springframework.stereotype.Component;

@Component
public class OwnerMapper {
    public OwnerResponseDTO toResponseDTO(Owner owner) {
        return new OwnerResponseDTO(
                owner.getId(),
                owner.getName(),
                owner.getEmail(),
                owner.getAddress(),
                owner.getLastUpdate()
        );
    }
}
