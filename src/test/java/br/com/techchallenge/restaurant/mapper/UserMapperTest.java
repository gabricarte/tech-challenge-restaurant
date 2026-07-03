package br.com.techchallenge.restaurant.mapper;

import br.com.techchallenge.restaurant.domain.dto.request.UserRequestDTO;
import br.com.techchallenge.restaurant.domain.dto.response.UserResponseDTO;
import br.com.techchallenge.restaurant.domain.entity.Owner;
import br.com.techchallenge.restaurant.domain.entity.User;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UserMapperTest {

    private final UserMapper mapper = new UserMapper();

    @Test
    void testToDTO_MapsAllFields() {

        User user = new User();
        user.setId(1L);
        user.setName("Leticia");
        user.setEmail("leticia@test.com");

        UserResponseDTO dto = mapper.toDTO(user);

        assertThat(dto.id()).isEqualTo(1L);
        assertThat(dto.name()).isEqualTo("Leticia");
        assertThat(dto.email()).isEqualTo("leticia@test.com");
    }

    @Test
    void testToEntity_MapsAllFields() {

        UserRequestDTO dto = new UserRequestDTO(
                "Leticia",
                "leticia@test.com",
                "123456",
                "Rua A"
        );

        Owner owner = mapper.toEntity(dto);

        assertThat(owner.getName()).isEqualTo("Leticia");
        assertThat(owner.getEmail()).isEqualTo("leticia@test.com");
        assertThat(owner.getPassword()).isEqualTo("123456");
        assertThat(owner.getAddress()).isEqualTo("Rua A");
    }
}