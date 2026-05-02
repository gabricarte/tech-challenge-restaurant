package br.com.techchallenge.restaurant.mapper;

import br.com.techchallenge.restaurant.domain.dto.RestaurantResponseDTO;
import br.com.techchallenge.restaurant.domain.entity.Restaurant;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RestaurantMapper {
    public RestaurantResponseDTO toDTO(Restaurant restaurant) {
        return new RestaurantResponseDTO(
                restaurant.getId(),
                restaurant.getName(),
                restaurant.getAddress(),
                restaurant.getCuisineType(),
                restaurant.getCapacity(),
                restaurant.getOwner() != null ? restaurant.getOwner().getName() : "Sem Proprietário"
        );
    }

    public List<RestaurantResponseDTO> toDTOList(List<Restaurant> restaurants) {
        return restaurants.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}