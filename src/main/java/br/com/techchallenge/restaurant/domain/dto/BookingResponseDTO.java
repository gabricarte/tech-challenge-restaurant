package br.com.techchallenge.restaurant.domain.dto;

import java.time.LocalDateTime;

public record BookingResponseDTO(
        Long id,
        LocalDateTime dateTime,
        Integer numberOfPeople,
        String status,
        String customerName,
        String restaurantName
) {}