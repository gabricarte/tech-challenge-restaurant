package br.com.techchallenge.restaurant.domain.dto;

import java.time.LocalDate;

public record ClientRequestDTO(
        String name,
        String email,
        String cpf,
        String login,
        String telefone,
        String password,
        LocalDate birthDate
) {}