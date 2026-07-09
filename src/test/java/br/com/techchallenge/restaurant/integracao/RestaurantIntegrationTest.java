package br.com.techchallenge.restaurant.integracao;

import br.com.techchallenge.restaurant.controller.RestaurantController;
import br.com.techchallenge.restaurant.domain.dto.response.RestaurantResponseDTO;
import br.com.techchallenge.restaurant.domain.entity.Restaurant;
import br.com.techchallenge.restaurant.mapper.RestaurantMapper;
import br.com.techchallenge.restaurant.service.RestaurantService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RestaurantController.class)
class RestaurantIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @MockitoBean
    private RestaurantService restaurantService;

    @MockitoBean
    private RestaurantMapper restaurantMapper;

    @Test
    void create() throws Exception {

        Restaurant restaurant = new Restaurant();

        RestaurantResponseDTO response = new RestaurantResponseDTO(
                1L,
                "Restaurante",
                "Rua A",
                "Italiana",
                100,
                LocalTime.of(8, 0),
                LocalTime.of(22, 0),
                "João"
        );

        when(restaurantService.create(any(), eq(1L))).thenReturn(restaurant);
        when(restaurantMapper.toDTO(any())).thenReturn(response);

        mockMvc.perform(post("/api/v1/restaurants/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(restaurant)))
                .andExpect(status().isCreated());
    }

    @Test
    void findById() throws Exception {

        Restaurant restaurant = new Restaurant();

        RestaurantResponseDTO response = new RestaurantResponseDTO(
                1L,
                "Restaurante",
                "Rua A",
                "Italiana",
                100,
                LocalTime.of(8, 0),
                LocalTime.of(22, 0),
                "João"
        );

        when(restaurantService.findById(1L)).thenReturn(restaurant);
        when(restaurantMapper.toDTO(any())).thenReturn(response);

        mockMvc.perform(get("/api/v1/restaurants/1"))
                .andExpect(status().isOk());
    }

    @Test
    void findAll() throws Exception {

        Restaurant restaurant = new Restaurant();

        RestaurantResponseDTO response = new RestaurantResponseDTO(
                1L,
                "Restaurante",
                "Rua A",
                "Italiana",
                100,
                LocalTime.of(8, 0),
                LocalTime.of(22, 0),
                "João"
        );

        when(restaurantService.findAll()).thenReturn(List.of(restaurant));
        when(restaurantMapper.toDTOList(any())).thenReturn(List.of(response));

        mockMvc.perform(get("/api/v1/restaurants"))
                .andExpect(status().isOk());
    }

    @Test
    void update() throws Exception {

        Restaurant restaurant = new Restaurant();

        when(restaurantService.update(eq(1L), any())).thenReturn(restaurant);

        mockMvc.perform(put("/api/v1/restaurants/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(restaurant)))
                .andExpect(status().isOk());
    }

    @Test
    void deleteRestaurant() throws Exception {

        doNothing().when(restaurantService).delete(1L);

        mockMvc.perform(delete("/api/v1/restaurants/1"))
                .andExpect(status().isNoContent());
    }
}