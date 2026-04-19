package br.com.techchallenge.restaurant.domain.dto;

import java.time.LocalDate;

public record ClientRequestDTO(
        String name,
        String email,
        String login,
        String password,
        String address,
        LocalDate birthDate
) {}