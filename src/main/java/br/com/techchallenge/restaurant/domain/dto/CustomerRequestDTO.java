package br.com.techchallenge.restaurant.domain.dto;

import java.time.LocalDate;

public record CustomerRequestDTO(
        String name,
        String email,
        String cpf,
        String login,
        String telephone,
        String address,
        String password,
        LocalDate birthDate
) {}