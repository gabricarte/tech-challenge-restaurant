package br.com.techchallenge.restaurant.controller.integracao;

import br.com.techchallenge.restaurant.controller.OwnerController;
import br.com.techchallenge.restaurant.domain.dto.response.OwnerResponseDTO;
import br.com.techchallenge.restaurant.domain.entity.UserType;
import br.com.techchallenge.restaurant.service.OwnerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OwnerController.class)
class OwnerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private OwnerService ownerService;

    @Test
    void findById() throws Exception {

        OwnerResponseDTO response = new OwnerResponseDTO(
                1L,
                "João",
                "joao@test.com",
                "Rua A",
                LocalDateTime.now(),
                new UserType(1L, "OWNER")
        );

        when(ownerService.findById(1L)).thenReturn(response);

        mockMvc.perform(
                        org.springframework.test.web.servlet.request.MockMvcRequestBuilders
                                .get("/api/v1/owners/1"))
                .andExpect(status().isOk());
    }
}