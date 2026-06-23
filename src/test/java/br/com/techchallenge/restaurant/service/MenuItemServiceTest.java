package br.com.techchallenge.restaurant.service;

import br.com.techchallenge.restaurant.domain.entity.MenuItem;
import br.com.techchallenge.restaurant.domain.entity.Restaurant;
import br.com.techchallenge.restaurant.exception.MenuItemNotFoundException;
import br.com.techchallenge.restaurant.exception.RestaurantNotFoundException;
import br.com.techchallenge.restaurant.repository.MenuItemRepository;
import br.com.techchallenge.restaurant.repository.RestaurantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MenuItemServiceTest {

    @Mock
    private MenuItemRepository menuItemRepository;

    @Mock
    private RestaurantRepository restaurantRepository;

    @InjectMocks
    private MenuItemService menuItemService;

    private MenuItem menuItem;
    private Restaurant restaurant;

    @BeforeEach
    void setUp() {
        restaurant = new Restaurant();
        restaurant.setId(1L);
        menuItem = new MenuItem();
        menuItem.setId(1L);
        menuItem.setName("Pizza");
    }

    @Test
    void testCreate_Success() {
        when(restaurantRepository.findById(1L)).thenReturn(Optional.of(restaurant));
        when(menuItemRepository.save(any())).thenReturn(menuItem);

        MenuItem result = menuItemService.create(menuItem, 1L);

        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo("Pizza");
        verify(menuItemRepository, times(1)).save(any());
    }

    @Test
    void testCreate_RestaurantNotFound_ThrowsException() {
        when(restaurantRepository.findById(999L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> menuItemService.create(menuItem, 999L))
                .isInstanceOf(RestaurantNotFoundException.class);
    }

    @Test
    void testFindById_Success() {
        when(menuItemRepository.findById(1L)).thenReturn(Optional.of(menuItem));

        MenuItem result = menuItemService.findById(1L);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1L);
    }

    @Test
    void testFindById_NotFound_ThrowsException() {
        when(menuItemRepository.findById(999L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> menuItemService.findById(999L))
                .isInstanceOf(MenuItemNotFoundException.class);
    }

    @Test
    void testFindAll_Success() {
        when(menuItemRepository.findAll()).thenReturn(List.of(menuItem));

        List<MenuItem> result = menuItemService.findAll();

        assertThat(result).hasSize(1);
    }

    @Test
    void testFindByRestaurant_Success() {
        when(restaurantRepository.findById(1L)).thenReturn(Optional.of(restaurant));
        when(menuItemRepository.findByRestaurantId(1L)).thenReturn(List.of(menuItem));

        List<MenuItem> result = menuItemService.findByRestaurant(1L);

        assertThat(result).hasSize(1);
    }

    @Test
    void testFindByRestaurant_RestaurantNotFound_ThrowsException() {
        when(restaurantRepository.findById(999L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> menuItemService.findByRestaurant(999L))
                .isInstanceOf(RestaurantNotFoundException.class);
    }

    @Test
    void testUpdate_Success() {
        MenuItem updated = new MenuItem();
        updated.setName("Burger");
        updated.setDescription("Delicious");
        updated.setPrice(BigDecimal.valueOf(25.0));
        updated.setAvailable(true);

        when(menuItemRepository.findById(1L)).thenReturn(Optional.of(menuItem));
        when(menuItemRepository.save(any())).thenReturn(menuItem);

        MenuItem result = menuItemService.update(1L, updated);

        assertThat(result).isNotNull();
        verify(menuItemRepository, times(1)).save(any());
    }

    @Test
    void testUpdate_NotFound_ThrowsException() {
        MenuItem updated = new MenuItem();
        when(menuItemRepository.findById(999L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> menuItemService.update(999L, updated))
                .isInstanceOf(MenuItemNotFoundException.class);
    }

    @Test
    void testDelete_Success() {
        when(menuItemRepository.findById(1L)).thenReturn(Optional.of(menuItem));
        doNothing().when(menuItemRepository).delete(any());

        menuItemService.delete(1L);

        verify(menuItemRepository, times(1)).delete(menuItem);
    }

    @Test
    void testDelete_NotFound_ThrowsException() {
        when(menuItemRepository.findById(999L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> menuItemService.delete(999L))
                .isInstanceOf(MenuItemNotFoundException.class);
    }
}