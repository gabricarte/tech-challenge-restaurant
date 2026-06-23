package br.com.techchallenge.restaurant.mapper;

import br.com.techchallenge.restaurant.domain.dto.response.BookingResponseDTO;
import br.com.techchallenge.restaurant.domain.entity.Booking;
import br.com.techchallenge.restaurant.domain.entity.Restaurant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class BookingMapperTest {

    private BookingMapper bookingMapper;

    @BeforeEach
    void setUp() {
        bookingMapper = new BookingMapper();
    }

    @Test
    void testToDTO_MapsAllFields() {
        Restaurant restaurant = new Restaurant();
        restaurant.setId(1L);
        restaurant.setName("Test R");

        Booking booking = new Booking();
        booking.setId(1L);
        booking.setDateTime(LocalDateTime.of(2026, 7, 1, 20, 0));
        booking.setNumberOfPeople(4);
        booking.setStatus("CONFIRMED");
        booking.setCustomerName("John");
        booking.setRestaurant(restaurant);

        BookingResponseDTO dto = bookingMapper.toDTO(booking);

        assertThat(dto.id()).isEqualTo(1L);
        assertThat(dto.customerName()).isEqualTo("John");
        assertThat(dto.restaurantName()).isEqualTo("Test R");
        assertThat(dto.status()).isEqualTo("CONFIRMED");
    }
}

