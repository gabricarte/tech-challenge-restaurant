package br.com.techchallenge.restaurant.service;

import br.com.techchallenge.restaurant.domain.entity.Owner;
import br.com.techchallenge.restaurant.domain.entity.Restaurant;
import br.com.techchallenge.restaurant.exception.RestaurantNotFoundException;
import br.com.techchallenge.restaurant.exception.UserNotFoundException;
import br.com.techchallenge.restaurant.repository.OwnerRepository;
import br.com.techchallenge.restaurant.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private OwnerRepository ownerRepository;

    public Restaurant cadastrar(Restaurant restaurant, Long ownerId) {
        Owner owner = ownerRepository.findById(ownerId)
                .orElseThrow(() -> new UserNotFoundException(ownerId));

        restaurant.setOwner(owner);
        return restaurantRepository.save(restaurant);
    }

    public Restaurant buscarPorId(Long id) {
        return restaurantRepository.findById(id)
                .orElseThrow(() -> new RestaurantNotFoundException(id));
    }

    public List<Restaurant> listarTodos() {
        return restaurantRepository.findAll();
    }

    public Restaurant atualizar(Long id, Restaurant restauranteAtualizado) {
        Restaurant restauranteExistente = buscarPorId(id);

        restauranteExistente.setName(restauranteAtualizado.getName());
        restauranteExistente.setAddress(restauranteAtualizado.getAddress());
        restauranteExistente.setCuisineType(restauranteAtualizado.getCuisineType());
        restauranteExistente.setCapacity(restauranteAtualizado.getCapacity());

        return restaurantRepository.save(restauranteExistente);
    }

    public void excluir(Long id) {
        Restaurant restaurante = buscarPorId(id);
        restaurantRepository.delete(restaurante);
    }
}