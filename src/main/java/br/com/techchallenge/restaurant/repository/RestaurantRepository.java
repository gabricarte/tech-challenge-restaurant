package br.com.techchallenge.restaurant.repository;

import br.com.techchallenge.restaurant.domain.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
        List<Restaurant> findByOwnerId(Long ownerId);
    }