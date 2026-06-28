package br.com.techchallenge.restaurant.domain.dto.response;

import java.time.LocalTime;

public record RestaurantResponseDTO(
        Long id,
        String name,
        String address,
        String cuisineType,
        Integer capacity,
        LocalTime openingTime,
        LocalTime closingTime,
        String ownerName
) {}
