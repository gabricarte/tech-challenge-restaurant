package br.com.techchallenge.restaurant.service;

import br.com.techchallenge.restaurant.domain.dto.RestaurantResponseDTO;
import br.com.techchallenge.restaurant.domain.entity.Owner;
import br.com.techchallenge.restaurant.domain.entity.Restaurant;
import br.com.techchallenge.restaurant.exception.RestaurantNotFoundException;
import br.com.techchallenge.restaurant.exception.UserNotFoundException;
import br.com.techchallenge.restaurant.mapper.RestaurantMapper;
import br.com.techchallenge.restaurant.repository.OwnerRepository;
import br.com.techchallenge.restaurant.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    private final OwnerRepository ownerRepository;

    private final RestaurantMapper restaurantMapper;

    public RestaurantResponseDTO save(Restaurant restaurant, Long ownerId) {
        Owner owner = ownerRepository.findById(ownerId)
                .orElseThrow(UserNotFoundException::new);

        restaurant.setOwner(owner);
        return restaurantMapper.toDTO(restaurantRepository.save(restaurant));
    }

    public Restaurant findEntityById(Long id) {
        return restaurantRepository.findById(id).orElseThrow(() -> new RestaurantNotFoundException(id));
    }

    public RestaurantResponseDTO findById(Long id) {
        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow(() -> new RestaurantNotFoundException(id));

        return restaurantMapper.toDTO(restaurant);
    }

    public List<RestaurantResponseDTO> listAll() {
        return restaurantRepository.findAll().stream().map(restaurantMapper::toDTO).toList();
    }

    public RestaurantResponseDTO update(Long id, Restaurant updatedRestaurant) {
        Restaurant existingRestaurant = findEntityById(id);

        existingRestaurant.setName(updatedRestaurant.getName());
        existingRestaurant.setAddress(updatedRestaurant.getAddress());
        existingRestaurant.setCuisineType(updatedRestaurant.getCuisineType());
        existingRestaurant.setCapacity(updatedRestaurant.getCapacity());

        return restaurantMapper.toDTO(restaurantRepository.save(existingRestaurant));
    }

    public void delete(Long id) {
        Restaurant restaurant = findEntityById(id);
        restaurantRepository.delete(restaurant);
    }
}