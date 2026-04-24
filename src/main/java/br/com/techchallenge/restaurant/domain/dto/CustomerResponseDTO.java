package br.com.techchallenge.restaurant.domain.dto;

import java.time.LocalDate;

public record CustomerResponseDTO(
        Long id,
        String login,
        String name,
        String email,
        LocalDate birthDate
) {}