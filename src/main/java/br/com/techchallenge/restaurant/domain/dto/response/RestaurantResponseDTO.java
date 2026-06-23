package br.com.techchallenge.restaurant.domain.dto.response;

public record RestaurantResponseDTO(
        Long id,
        String name,
        String address,
        String cuisineType,
        Integer capacity,
        String ownerName
) {}
