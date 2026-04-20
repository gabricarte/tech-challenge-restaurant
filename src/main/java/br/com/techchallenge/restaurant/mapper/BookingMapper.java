package br.com.techchallenge.restaurant.mapper;

import br.com.techchallenge.restaurant.domain.dto.BookingResponseDTO;
import br.com.techchallenge.restaurant.domain.entity.Booking;
import org.springframework.stereotype.Component;

@Component
public class BookingMapper {
public BookingResponseDTO toDTO(Booking booking) {
        return new BookingResponseDTO(
                booking.getId(),
                booking.getDateTime(),
                booking.getNumberOfPeople(),
                booking.getStatus(),
                booking.getCustomerName(),
                booking.getRestaurant().getName()
        );
    }
}