package br.com.techchallenge.restaurant.service;

import br.com.techchallenge.restaurant.domain.entity.Booking;
import br.com.techchallenge.restaurant.domain.entity.Restaurant;
import br.com.techchallenge.restaurant.exception.RestaurantOverCapacityException;
import br.com.techchallenge.restaurant.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookingService {
    private final BookingRepository bookingRepository;

    private final RestaurantService restaurantService;

    public Booking createBooking(Booking booking, Long restaurantId) {
        Restaurant restaurant = restaurantService.findEntityById(restaurantId);

        Integer currentOccupation = bookingRepository.sumPeopleByRestaurantAndDateTime(restaurantId, booking.getDateTime());

        if (currentOccupation == null) currentOccupation = 0;

        int availableSeats = restaurant.getCapacity() - currentOccupation;

        if (booking.getNumberOfPeople() > availableSeats) {
            throw new RestaurantOverCapacityException("Capacidade insuficiente! O restaurante possui apenas "
                    + (Math.max(availableSeats, 0)) + " vagas disponíveis para este horário.");
        }

        booking.setRestaurant(restaurant);
        booking.setStatus("CONFIRMED");

        return bookingRepository.save(booking);
    }
}