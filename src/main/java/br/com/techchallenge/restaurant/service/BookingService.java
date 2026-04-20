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

    public Booking realizarReserva(Booking booking, Long restaurantId) {
        Restaurant restaurant = restaurantService.buscarPorId(restaurantId);

        Integer ocupacaoAtual = bookingRepository.sumPeopleByRestaurantAndDateTime(restaurantId, booking.getDateTime());
        if (ocupacaoAtual == null) ocupacaoAtual = 0;

        int vagasRestantes = restaurant.getCapacity() - ocupacaoAtual;

        if (booking.getNumberOfPeople() > vagasRestantes) {
            throw new RestaurantOverCapacityException("Capacidade insuficiente! O restaurante possui apenas "
                    + (vagasRestantes < 0 ? 0 : vagasRestantes) + " vagas disponíveis para este horário.");
        }

        booking.setRestaurant(restaurant);
        booking.setStatus("CONFIRMED");
        return bookingRepository.save(booking);
    }
}