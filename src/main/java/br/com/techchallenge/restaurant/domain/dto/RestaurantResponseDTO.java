package br.com.techchallenge.restaurant.domain.dto;

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
