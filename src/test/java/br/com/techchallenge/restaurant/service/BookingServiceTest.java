package br.com.techchallenge.restaurant.service;

import br.com.techchallenge.restaurant.domain.entity.Booking;
import br.com.techchallenge.restaurant.domain.entity.Restaurant;
import br.com.techchallenge.restaurant.exception.RestaurantOverCapacityException;
import br.com.techchallenge.restaurant.repository.BookingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookingServiceTest {

	@Mock
	private BookingRepository bookingRepository;

	@Mock
	private RestaurantService restaurantService;

	@InjectMocks
	private BookingService bookingService;

	private Restaurant restaurant;

	@BeforeEach
	void setUp() {
		restaurant = new Restaurant();
		restaurant.setId(1L);
		restaurant.setCapacity(10);
	}

    @Test
    void testCreate_ValidBooking_Success() {
        Booking booking = new Booking();
        booking.setDateTime(LocalDateTime.now());
        booking.setNumberOfPeople(3);

        when(restaurantService.findById(1L)).thenReturn(restaurant);
        when(bookingRepository.sumPeopleByRestaurantAndDateTime(eq(1L), any())).thenReturn(0);
        when(bookingRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        Booking result = bookingService.create(booking, 1L);

        assertThat(result).isNotNull();
        assertThat(result.getStatus()).isEqualTo("CONFIRMED");
        assertThat(result.getRestaurant()).isEqualTo(restaurant);
        assertThat(result.getNumberOfPeople()).isEqualTo(3);
        verify(bookingRepository, times(1)).save(any());
    }

    @Test
    void testCreate_ExceedCapacity_ThrowsException() {
        Booking booking = new Booking();
        booking.setDateTime(LocalDateTime.now());
        booking.setNumberOfPeople(5);

        when(restaurantService.findById(1L)).thenReturn(restaurant);
        when(bookingRepository.sumPeopleByRestaurantAndDateTime(eq(1L), any())).thenReturn(8);

        assertThatThrownBy(() -> bookingService.create(booking, 1L))
                .isInstanceOf(RestaurantOverCapacityException.class)
                .hasMessageContaining("insuficiente");

        verify(bookingRepository, never()).save(any());
    }

    @Test
    void testCreate_NullOccupancy_DefaultsToZero() {
        Booking booking = new Booking();
        booking.setDateTime(LocalDateTime.now());
        booking.setNumberOfPeople(2);

        when(restaurantService.findById(1L)).thenReturn(restaurant);
        when(bookingRepository.sumPeopleByRestaurantAndDateTime(eq(1L), any())).thenReturn(null);
        when(bookingRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        Booking result = bookingService.create(booking, 1L);

        assertThat(result).isNotNull();
        assertThat(result.getStatus()).isEqualTo("CONFIRMED");
        verify(bookingRepository, times(1)).save(any());
    }

    @Test
    void testCreate_ExactCapacity_Success() {
        restaurant.setCapacity(10);
        Booking booking = new Booking();
        booking.setDateTime(LocalDateTime.now());
        booking.setNumberOfPeople(10);

        when(restaurantService.findById(1L)).thenReturn(restaurant);
        when(bookingRepository.sumPeopleByRestaurantAndDateTime(eq(1L), any())).thenReturn(0);
        when(bookingRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        Booking result = bookingService.create(booking, 1L);

        assertThat(result.getStatus()).isEqualTo("CONFIRMED");
        verify(bookingRepository, times(1)).save(any());
    }

    @Test
    void testCreate_RestaurantNotFound_ThrowsException() {
        Booking booking = new Booking();
        booking.setDateTime(LocalDateTime.now());
        booking.setNumberOfPeople(2);

        when(restaurantService.findById(999L)).thenThrow(new RuntimeException("Restaurant not found"));

        assertThatThrownBy(() -> bookingService.create(booking, 999L))
                .isInstanceOf(RuntimeException.class);

        verify(bookingRepository, never()).save(any());
    }

}


