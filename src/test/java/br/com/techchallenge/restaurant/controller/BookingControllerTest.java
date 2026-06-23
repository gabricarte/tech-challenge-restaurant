package br.com.techchallenge.restaurant.controller;

import br.com.techchallenge.restaurant.domain.dto.response.BookingResponseDTO;
import br.com.techchallenge.restaurant.domain.entity.Booking;
import br.com.techchallenge.restaurant.mapper.BookingMapper;
import br.com.techchallenge.restaurant.repository.BookingRepository;
import br.com.techchallenge.restaurant.service.BookingService;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookingControllerTest {

    @Test
    void testFindAll() {

        BookingRepository bookingRepository = mock(BookingRepository.class);
        BookingMapper bookingMapper = mock(BookingMapper.class);
        BookingService bookingService = mock(BookingService.class);

        BookingController controller = new BookingController();

        ReflectionTestUtils.setField(controller, "bookingRepository", bookingRepository);
        ReflectionTestUtils.setField(controller, "bookingMapper", bookingMapper);
        ReflectionTestUtils.setField(controller, "bookingService", bookingService);

        Booking booking = new Booking();

        BookingResponseDTO dto = new BookingResponseDTO(
                1L,
                LocalDateTime.now(),
                4,
                "CONFIRMED",
                "João",
                "Restaurante Teste"
        );

        when(bookingRepository.findAll())
                .thenReturn(List.of(booking));

        when(bookingMapper.toDTO(booking))
                .thenReturn(dto);

        ResponseEntity<List<BookingResponseDTO>> response =
                controller.findAll();

        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        assertEquals(1L, response.getBody().get(0).id());

        verify(bookingRepository).findAll();
    }
}