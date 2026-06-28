package br.com.techchallenge.restaurant.mapper;

import br.com.techchallenge.restaurant.domain.dto.response.RestaurantResponseDTO;
import br.com.techchallenge.restaurant.domain.entity.Owner;
import br.com.techchallenge.restaurant.domain.entity.Restaurant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RestaurantMapperTest {

    private RestaurantMapper restaurantMapper;

    @BeforeEach
    void setUp() {
        restaurantMapper = new RestaurantMapper();
    }

    @Test
    void testToDTO_MapsAllFields() {
        Owner owner = new Owner();
        owner.setId(1L);
        owner.setName("Owner 1");

        Restaurant restaurant = new Restaurant();
        restaurant.setId(1L);
        restaurant.setName("Restaurant 1");
        restaurant.setAddress("Address");
        restaurant.setCuisineType("Italiana");
        restaurant.setCapacity(30);
        restaurant.setOpeningTime(LocalTime.of(10, 0));
        restaurant.setClosingTime(LocalTime.of(22, 0));
        restaurant.setOwner(owner);

        RestaurantResponseDTO dto = restaurantMapper.toDTO(restaurant);

        assertThat(dto.id()).isEqualTo(1L);
        assertThat(dto.name()).isEqualTo("Restaurant 1");
        assertThat(dto.address()).isEqualTo("Address");
        assertThat(dto.cuisineType()).isEqualTo("Italiana");
        assertThat(dto.capacity()).isEqualTo(30);
        assertThat(dto.ownerName()).isEqualTo("Owner 1");
    }

    @Test
    void testToDTO_OwnerNull_DefaultsToSemProprietario() {
        Restaurant restaurant = new Restaurant();
        restaurant.setId(1L);
        restaurant.setName("Restaurant 1");
        restaurant.setCapacity(30);
        restaurant.setOwner(null);

        RestaurantResponseDTO dto = restaurantMapper.toDTO(restaurant);

        assertThat(dto.ownerName()).isEqualTo("Sem Proprietário");
    }

    @Test
    void testToDTOList_ConvertsList() {
        Owner owner = new Owner();
        owner.setName("Owner");

        Restaurant r1 = new Restaurant();
        r1.setId(1L);
        r1.setName("R1");
        r1.setCapacity(10);
        r1.setOwner(owner);

        Restaurant r2 = new Restaurant();
        r2.setId(2L);
        r2.setName("R2");
        r2.setCapacity(20);
        r2.setOwner(owner);

        List<RestaurantResponseDTO> dtos = restaurantMapper.toDTOList(List.of(r1, r2));

        assertThat(dtos).hasSize(2);
        assertThat(dtos.get(0).name()).isEqualTo("R1");
        assertThat(dtos.get(1).name()).isEqualTo("R2");
    }
}

