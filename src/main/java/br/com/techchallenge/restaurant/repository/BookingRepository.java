package br.com.techchallenge.restaurant.repository;

import br.com.techchallenge.restaurant.domain.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    @Query("SELECT SUM(b.numberOfPeople) FROM Booking b WHERE b.restaurant.id = :restaurantId AND b.dateTime = :dateTime AND b.status = 'CONFIRMED'")
    Integer sumPeopleByRestaurantAndDateTime(Long restaurantId, LocalDateTime dateTime);
}
