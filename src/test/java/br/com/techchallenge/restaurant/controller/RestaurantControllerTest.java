package br.com.techchallenge.restaurant.controller;

import br.com.techchallenge.restaurant.domain.dto.response.RestaurantResponseDTO;
import br.com.techchallenge.restaurant.domain.entity.Restaurant;
import br.com.techchallenge.restaurant.mapper.RestaurantMapper;
import br.com.techchallenge.restaurant.service.RestaurantService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RestaurantControllerTest {

    @Mock
    private RestaurantService restaurantService;

    @Mock
    private RestaurantMapper restaurantMapper;

    @InjectMocks
    private RestaurantController controller;

    private Restaurant restaurant;
    private RestaurantResponseDTO restaurantDTO;

    @BeforeEach
    void setUp() {
        restaurant = new Restaurant();
        restaurant.setId(1L);
        restaurant.setName("Test Restaurant");

        restaurantDTO = new RestaurantResponseDTO(1L, "Test Restaurant", "Addr", "Italiana", 50, "Owner");
    }

    @Test
    void testCreate_Success() {
        when(restaurantService.create(any(), eq(1L))).thenReturn(restaurant);
        when(restaurantMapper.toDTO(restaurant)).thenReturn(restaurantDTO);

        ResponseEntity<RestaurantResponseDTO> response = controller.create(restaurant, 1L);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();
        verify(restaurantService, times(1)).create(any(), eq(1L));
    }

    @Test
    void testFindById_Success() {
        when(restaurantService.findById(1L)).thenReturn(restaurant);
        when(restaurantMapper.toDTO(restaurant)).thenReturn(restaurantDTO);

        ResponseEntity<RestaurantResponseDTO> response = controller.findById(1L);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
    }

    @Test
    void testFindAll_Success() {
        when(restaurantService.findAll()).thenReturn(List.of(restaurant));
        when(restaurantMapper.toDTOList(any())).thenReturn(List.of(restaurantDTO));

        ResponseEntity<List<RestaurantResponseDTO>> response = controller.findAll();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).hasSize(1);
    }

    @Test
    void testUpdate_Success() {
        when(restaurantService.update(eq(1L), any())).thenReturn(restaurant);

        ResponseEntity<Restaurant> response = controller.update(1L, restaurant);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        verify(restaurantService, times(1)).update(eq(1L), any());
    }

    @Test
    void testDelete_Success() {
        doNothing().when(restaurantService).delete(1L);

        ResponseEntity<Void> response = controller.delete(1L);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        verify(restaurantService, times(1)).delete(1L);
    }
}

