package br.com.techchallenge.restaurant.controller;

import br.com.techchallenge.restaurant.domain.dto.response.BookingResponseDTO;
import br.com.techchallenge.restaurant.domain.entity.Booking;
import br.com.techchallenge.restaurant.mapper.BookingMapper;
import br.com.techchallenge.restaurant.repository.BookingRepository;
import br.com.techchallenge.restaurant.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private BookingMapper bookingMapper;

    @Autowired
    private BookingRepository bookingRepository;

    @PostMapping("/{restaurantId}")
    public ResponseEntity<BookingResponseDTO> create(@RequestBody Booking booking, @PathVariable Long restaurantId) {
        Booking newBooking = bookingService.create(booking, restaurantId);
        return ResponseEntity.status(201).body(bookingMapper.toDTO(newBooking));
    }

    @GetMapping
    public ResponseEntity<List<BookingResponseDTO>> findAll() {
        List<Booking> bookings = bookingRepository.findAll();
        List<BookingResponseDTO> dtos = bookings.stream()
                .map(bookingMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }
}