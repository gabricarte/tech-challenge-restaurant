package br.com.techchallenge.restaurant.mapper;

import br.com.techchallenge.restaurant.domain.dto.UserResponseDTO;
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
}
