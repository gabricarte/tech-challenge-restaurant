package br.com.techchallenge.restaurant.domain.dto;

public record RestaurantResponseDTO(
        Long id,
        String name,
        String address,
        String cuisineType,
        Integer capacity,
        String ownerName
) {}
