package br.com.techchallenge.restaurant.domain.dto;

import java.time.LocalDate;

public record ClientResponseDTO(
        Long id,
        String login,
        String name,
        String email,
        LocalDate birthDate
) {}