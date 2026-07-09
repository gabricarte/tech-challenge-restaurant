package br.com.techchallenge.restaurant.integracao;

import br.com.techchallenge.restaurant.controller.BookingController;
import br.com.techchallenge.restaurant.domain.dto.response.BookingResponseDTO;
import br.com.techchallenge.restaurant.domain.entity.Booking;
import br.com.techchallenge.restaurant.mapper.BookingMapper;
import br.com.techchallenge.restaurant.repository.BookingRepository;
import br.com.techchallenge.restaurant.service.BookingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookingController.class)
class BookingIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @MockitoBean
    private BookingService bookingService;

    @MockitoBean
    private BookingMapper bookingMapper;

    @MockitoBean
    private BookingRepository bookingRepository;

    @Test
    void createBooking() throws Exception {

        Booking booking = new Booking();

        BookingResponseDTO response = null;

        when(bookingService.createBooking(any(), eq(1L))).thenReturn(booking);
        when(bookingMapper.toDTO(any())).thenReturn(response);

        mockMvc.perform(post("/api/v1/bookings/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(booking)))
                .andExpect(status().isCreated());
    }

    @Test
    void listAll() throws Exception {

        Booking booking = new Booking();

        BookingResponseDTO response = null;

        when(bookingRepository.findAll()).thenReturn(List.of(booking));
        when(bookingMapper.toDTO(any())).thenReturn(response);

        mockMvc.perform(get("/api/v1/bookings"))
                .andExpect(status().isOk());
    }
}