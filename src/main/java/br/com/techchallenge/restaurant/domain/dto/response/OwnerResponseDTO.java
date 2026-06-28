package br.com.techchallenge.restaurant.domain.dto.response;

import br.com.techchallenge.restaurant.domain.entity.UserType;

import java.time.LocalDateTime;

public record OwnerResponseDTO(
        Long id,
        String name,
        String email,
        String address,
        LocalDateTime lastUpdate,
        UserType userType
) {}
