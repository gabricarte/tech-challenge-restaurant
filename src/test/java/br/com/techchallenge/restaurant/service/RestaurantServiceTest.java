package br.com.techchallenge.restaurant.service;

import br.com.techchallenge.restaurant.domain.entity.Owner;
import br.com.techchallenge.restaurant.domain.entity.Restaurant;
import br.com.techchallenge.restaurant.exception.OwnerNotFoundException;
import br.com.techchallenge.restaurant.exception.RestaurantNotFoundException;
import br.com.techchallenge.restaurant.repository.OwnerRepository;
import br.com.techchallenge.restaurant.repository.RestaurantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RestaurantServiceTest {

    @Mock
    private RestaurantRepository restaurantRepository;

    @Mock
    private OwnerRepository ownerRepository;

    @InjectMocks
    private RestaurantService restaurantService;

    private Owner owner;
    private Restaurant restaurant;

    @BeforeEach
    void setUp() {
        owner = new Owner();
        owner.setId(1L);
        owner.setName("Owner 1");

        restaurant = new Restaurant();
        restaurant.setId(1L);
        restaurant.setName("R1");
        restaurant.setAddress("Addr");
        restaurant.setCuisineType("Italiana");
        restaurant.setCapacity(50);
        restaurant.setOpeningTime(LocalTime.of(10,0));
        restaurant.setClosingTime(LocalTime.of(22,0));
    }

    @Test
    void testCreate_ValidRestaurant_Success() {
        when(ownerRepository.findById(1L)).thenReturn(Optional.of(owner));
        when(restaurantRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        Restaurant result = restaurantService.create(restaurant, 1L);

        assertThat(result).isNotNull();
        assertThat(result.getOwner()).isEqualTo(owner);
        assertThat(result.getName()).isEqualTo("R1");
        verify(restaurantRepository, times(1)).save(any());
    }

    @Test
    void testCreate_OwnerNotFound_ThrowsException() {
        when(ownerRepository.findById(999L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> restaurantService.create(restaurant, 999L))
                .isInstanceOf(OwnerNotFoundException.class);

        verify(restaurantRepository, never()).save(any());
    }

    @Test
    void testFindById_Found_ReturnsRestaurant() {
        when(restaurantRepository.findById(1L)).thenReturn(Optional.of(restaurant));

        Restaurant result = restaurantService.findById(1L);

        assertThat(result).isEqualTo(restaurant);
        assertThat(result.getName()).isEqualTo("R1");
        assertThat(result.getCapacity()).isEqualTo(50);
    }

    @Test
    void testFindById_NotFound_ThrowsException() {
        when(restaurantRepository.findById(2L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> restaurantService.findById(2L))
                .isInstanceOf(RestaurantNotFoundException.class);
    }

    @Test
    void testFindAll_ReturnsAllRestaurants() {
        when(restaurantRepository.findAll()).thenReturn(List.of(restaurant));

        var list = restaurantService.findAll();

        assertThat(list).hasSize(1);
        assertThat(list.get(0).getName()).isEqualTo("R1");
    }

    @Test
    void testFindAll_EmptyList_ReturnsEmpty() {
        when(restaurantRepository.findAll()).thenReturn(List.of());

        var list = restaurantService.findAll();

        assertThat(list).isEmpty();
    }

    @Test
    void testUpdate_ChangesAllFields() {
        Restaurant updated = new Restaurant();
        updated.setName("NewName");
        updated.setAddress("NewAddr");
        updated.setCuisineType("Japonesa");
        updated.setCapacity(30);
        updated.setOpeningTime(LocalTime.of(9,0));
        updated.setClosingTime(LocalTime.of(23,0));

        when(restaurantRepository.findById(1L)).thenReturn(Optional.of(restaurant));
        when(restaurantRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        Restaurant result = restaurantService.update(1L, updated);

        assertThat(result.getName()).isEqualTo("NewName");
        assertThat(result.getCapacity()).isEqualTo(30);
        assertThat(result.getAddress()).isEqualTo("NewAddr");
        assertThat(result.getCuisineType()).isEqualTo("Japonesa");
    }

    @Test
    void testUpdate_NotFound_ThrowsException() {
        Restaurant updated = new Restaurant();
        when(restaurantRepository.findById(999L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> restaurantService.update(999L, updated))
                .isInstanceOf(RestaurantNotFoundException.class);
    }

    @Test
    void testDelete_RemovesRestaurant() {
        when(restaurantRepository.findById(1L)).thenReturn(Optional.of(restaurant));
        doNothing().when(restaurantRepository).delete(any());

        restaurantService.delete(1L);

        verify(restaurantRepository, times(1)).delete(restaurant);
    }

    @Test
    void testDelete_NotFound_ThrowsException() {
        when(restaurantRepository.findById(999L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> restaurantService.delete(999L))
                .isInstanceOf(RestaurantNotFoundException.class);
    }
}

