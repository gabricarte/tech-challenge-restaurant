package br.com.techchallenge.restaurant.mapper;

import br.com.techchallenge.restaurant.domain.dto.UserRequestDTO;
import br.com.techchallenge.restaurant.domain.dto.UserResponseDTO;
import br.com.techchallenge.restaurant.domain.entity.Owner;
import br.com.techchallenge.restaurant.domain.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserResponseDTO toDTO(User user) {
        return new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }

    public Owner toEntity(UserRequestDTO dto) {
        Owner owner = new Owner();
        owner.setName(dto.name());
        owner.setEmail(dto.email());
        owner.setPassword(dto.password());
        owner.setAddress(dto.address());
        return owner;
    }
}
