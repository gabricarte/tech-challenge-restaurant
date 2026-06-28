package br.com.techchallenge.restaurant.controller;

import br.com.techchallenge.restaurant.domain.dto.response.BookingResponseDTO;
import br.com.techchallenge.restaurant.domain.entity.Booking;
import br.com.techchallenge.restaurant.mapper.BookingMapper;
import br.com.techchallenge.restaurant.repository.BookingRepository;
import br.com.techchallenge.restaurant.service.BookingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookingControllerTest {

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private BookingMapper bookingMapper;

    @Mock
    private BookingService bookingService;

    @InjectMocks
    private BookingController controller;

    private Booking booking;
    private BookingResponseDTO responseDTO;

    @BeforeEach
    void setUp() {
        booking = new Booking();
        booking.setId(1L);
        booking.setDateTime(LocalDateTime.now());
        booking.setNumberOfPeople(4);
        booking.setStatus("CONFIRMED");
        booking.setCustomerName("João");

        responseDTO = new BookingResponseDTO(1L, LocalDateTime.now(), 4, "CONFIRMED", "João", "Restaurante");
    }

    @Test
    void testCreate_Success() {
        when(bookingService.create(any(), eq(1L))).thenReturn(booking);
        when(bookingMapper.toDTO(booking)).thenReturn(responseDTO);

        ResponseEntity<BookingResponseDTO> response = controller.create(booking, 1L);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();
        verify(bookingService, times(1)).create(any(), eq(1L));
    }

    @Test
    void testFindAll_Success() {
        when(bookingRepository.findAll()).thenReturn(List.of(booking));
        when(bookingMapper.toDTO(booking)).thenReturn(responseDTO);

        ResponseEntity<List<BookingResponseDTO>> response = controller.findAll();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).hasSize(1);
        assertThat(response.getBody().get(0).id()).isEqualTo(1L);
        verify(bookingRepository, times(1)).findAll();
    }

    @Test
    void testFindAll_Empty() {
        when(bookingRepository.findAll()).thenReturn(List.of());

        ResponseEntity<List<BookingResponseDTO>> response = controller.findAll();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEmpty();
    }
}