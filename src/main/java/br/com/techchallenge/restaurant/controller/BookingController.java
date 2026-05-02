package br.com.techchallenge.restaurant.controller;

import br.com.techchallenge.restaurant.domain.dto.BookingResponseDTO;
import br.com.techchallenge.restaurant.domain.entity.Booking;
import br.com.techchallenge.restaurant.mapper.BookingMapper;
import br.com.techchallenge.restaurant.repository.BookingRepository;
import br.com.techchallenge.restaurant.service.BookingService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Tag(name = "Reservas", description = "Endpoints para gestão de reservas")
@RequestMapping("/api/v1/bookings")
public class BookingController {
    private final BookingService bookingService;

    private final BookingMapper bookingMapper;

    private final BookingRepository bookingRepository;

    @PostMapping("/{restaurantId}")
    public ResponseEntity<BookingResponseDTO> createBooking(@RequestBody Booking booking, @PathVariable Long restaurantId) {
        Booking newBooking = bookingService.createBooking(booking, restaurantId);
        return ResponseEntity.status(HttpStatus.CREATED).body(bookingMapper.toDTO(newBooking));
    }

    @GetMapping
    public ResponseEntity<List<BookingResponseDTO>> listAll() {
        List<Booking> bookings = bookingRepository.findAll();
        List<BookingResponseDTO> dtos = bookings.stream()
                .map(bookingMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }
}