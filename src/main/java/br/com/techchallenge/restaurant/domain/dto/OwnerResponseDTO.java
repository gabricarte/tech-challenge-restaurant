package br.com.techchallenge.restaurant.domain.dto;

import java.time.LocalDateTime;

public record OwnerResponseDTO(
        Long id,
        String name,
        String email,
        String address,
        LocalDateTime lastUpdate
) {}
