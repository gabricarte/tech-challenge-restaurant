package br.com.techchallenge.restaurant.domain.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ClientResponseDTO(
        Long id,
        String name,
        String email,
        String address,
        LocalDate birthDate,
        LocalDateTime lastUpdate
) {}
