package br.com.techchallenge.restaurant.domain.dto.response;

public record UserResponseDTO(
        Long id,
        String name,
        String email
) {}
