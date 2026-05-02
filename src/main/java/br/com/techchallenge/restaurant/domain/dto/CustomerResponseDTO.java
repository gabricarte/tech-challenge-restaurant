package br.com.techchallenge.restaurant.domain.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record CustomerResponseDTO(
        Long id,
        String login,
        String cpf,
        String telephone,
        String name,
        String email,
        LocalDate birthDate,
        LocalDateTime lastUpdate
) {}