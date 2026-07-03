package br.com.techchallenge.restaurant.mapper;

import br.com.techchallenge.restaurant.domain.dto.response.OwnerResponseDTO;
import br.com.techchallenge.restaurant.domain.entity.Owner;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class OwnerMapperTest {

    private final OwnerMapper mapper = new OwnerMapper();
    private Object UserType;

    @Test
    void testToResponseDTO_MapsAllFields() {

        LocalDateTime now = LocalDateTime.now();

        Owner owner = new Owner();
        owner.setId(1L);
        owner.setName("Owner");
        owner.setEmail("owner@test.com");
        owner.setAddress("Rua A");
        owner.setLastUpdate(now);
        OwnerResponseDTO dto = mapper.toResponseDTO(owner);

        assertThat(dto.id()).isEqualTo(1L);
        assertThat(dto.name()).isEqualTo("Owner");
        assertThat(dto.email()).isEqualTo("owner@test.com");
        assertThat(dto.address()).isEqualTo("Rua A");
        assertThat(dto.lastUpdate()).isEqualTo(now);
    }
}