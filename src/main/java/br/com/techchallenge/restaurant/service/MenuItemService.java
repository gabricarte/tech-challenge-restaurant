package br.com.techchallenge.restaurant.service;

import br.com.techchallenge.restaurant.domain.entity.MenuItem;
import br.com.techchallenge.restaurant.domain.entity.Restaurant;
import br.com.techchallenge.restaurant.exception.MenuItemNotFoundException;
import br.com.techchallenge.restaurant.exception.RestaurantNotFoundException;
import br.com.techchallenge.restaurant.repository.MenuItemRepository;
import br.com.techchallenge.restaurant.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuItemService {
    private final MenuItemRepository menuItemRepository;

    private final RestaurantRepository restaurantRepository;

    public MenuItem create(MenuItem menuItem, Long restaurantId) {

        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RestaurantNotFoundException(restaurantId));

        menuItem.setRestaurant(restaurant);

        return menuItemRepository.save(menuItem);
    }

    public MenuItem findById(Long id) {
        return menuItemRepository.findById(id)
                .orElseThrow(() -> new MenuItemNotFoundException(id));
    }

    public List<MenuItem> findAll() {
        return menuItemRepository.findAll();
    }

    public List<MenuItem> findByRestaurant(Long restaurantId) {

        restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RestaurantNotFoundException(restaurantId));

        return menuItemRepository.findByRestaurantId(restaurantId);
    }

    public MenuItem update(Long id, MenuItem updatedMenuItem) {

        MenuItem existingMenuItem = findById(id);

        existingMenuItem.setName(updatedMenuItem.getName());
        existingMenuItem.setDescription(updatedMenuItem.getDescription());
        existingMenuItem.setPrice(updatedMenuItem.getPrice());
        existingMenuItem.setAvailable(updatedMenuItem.getAvailable());

        return menuItemRepository.save(existingMenuItem);
    }

    public void delete(Long id) {

        MenuItem menuItem = findById(id);

        menuItemRepository.delete(menuItem);
    }
}