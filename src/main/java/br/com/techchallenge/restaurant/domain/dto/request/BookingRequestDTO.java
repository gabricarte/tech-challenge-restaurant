package br.com.techchallenge.restaurant.domain.dto.request;

import java.time.LocalDateTime;

public record BookingRequestDTO(
        LocalDateTime dateTime,
        Integer numberOfPeople,
        Long customerId,
        Long restaurantId
) {}