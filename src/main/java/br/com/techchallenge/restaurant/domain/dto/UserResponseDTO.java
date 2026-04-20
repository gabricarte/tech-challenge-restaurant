package br.com.techchallenge.restaurant.domain.dto;

public record UserResponseDTO(
        Long id,
        String nome,
        String email
) {}
