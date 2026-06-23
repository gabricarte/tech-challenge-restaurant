package br.com.techchallenge.restaurant.service;

import br.com.techchallenge.restaurant.domain.entity.Booking;
import br.com.techchallenge.restaurant.domain.entity.Restaurant;
import br.com.techchallenge.restaurant.exception.RestaurantOverCapacityException;
import br.com.techchallenge.restaurant.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private RestaurantService restaurantService;

    public Booking create(Booking booking, Long restaurantId) {
        Restaurant restaurant = restaurantService.findById(restaurantId);

        Integer currentOccupancy = bookingRepository.sumPeopleByRestaurantAndDateTime(restaurantId, booking.getDateTime());
        if (currentOccupancy == null) currentOccupancy = 0;

        int availableSpots = restaurant.getCapacity() - currentOccupancy;

        if (booking.getNumberOfPeople() > availableSpots) {
            throw new RestaurantOverCapacityException("Capacidade insuficiente! O restaurante possui apenas "
                    + (availableSpots < 0 ? 0 : availableSpots) + " vagas disponíveis para este horário.");
        }

        booking.setRestaurant(restaurant);
        booking.setStatus("CONFIRMED");
        return bookingRepository.save(booking);
    }
}