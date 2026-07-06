package br.com.techchallenge.restaurant.service;

import br.com.techchallenge.restaurant.domain.entity.Owner;
import br.com.techchallenge.restaurant.domain.entity.Restaurant;
import br.com.techchallenge.restaurant.exception.OwnerNotFoundException;
import br.com.techchallenge.restaurant.exception.RestaurantNotFoundException;
import br.com.techchallenge.restaurant.repository.OwnerRepository;
import br.com.techchallenge.restaurant.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    private final OwnerRepository ownerRepository;

    public Restaurant create(Restaurant restaurant, Long ownerId) {

        Owner owner = ownerRepository.findById(ownerId)
                .orElseThrow(() -> new OwnerNotFoundException(ownerId));

        restaurant.setOwner(owner);

        return restaurantRepository.save(restaurant);
    }

    public Restaurant findById(Long id) {

        return restaurantRepository.findById(id)
                .orElseThrow(() -> new RestaurantNotFoundException(id));
    }

    public List<Restaurant> findAll() {

        return restaurantRepository.findAll();
    }

    public Restaurant update(Long id, Restaurant updatedRestaurant) {

        Restaurant existingRestaurant = findById(id);

        existingRestaurant.setName(updatedRestaurant.getName());
        existingRestaurant.setAddress(updatedRestaurant.getAddress());
        existingRestaurant.setCuisineType(updatedRestaurant.getCuisineType());
        existingRestaurant.setCapacity(updatedRestaurant.getCapacity());
        existingRestaurant.setOpeningTime(updatedRestaurant.getOpeningTime());
        existingRestaurant.setClosingTime(updatedRestaurant.getClosingTime());

        return restaurantRepository.save(existingRestaurant);
    }

    public void delete(Long id) {

        Restaurant restaurant = findById(id);

        restaurantRepository.delete(restaurant);
    }
}