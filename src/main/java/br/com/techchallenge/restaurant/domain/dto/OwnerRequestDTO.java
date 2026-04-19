package br.com.techchallenge.restaurant.domain.dto;

import java.time.LocalDateTime;

public record OwnerRequestDTO(
        String name,
        String email,
        String login,
        String password,
        String address,
        LocalDateTime lastUpdate
) {}

