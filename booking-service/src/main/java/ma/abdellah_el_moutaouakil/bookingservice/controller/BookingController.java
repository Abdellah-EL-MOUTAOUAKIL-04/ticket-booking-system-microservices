package ma.abdellah_el_moutaouakil.bookingservice.controller;

import ma.abdellah_el_moutaouakil.bookingservice.dtos.BookingRequestDTO;
import ma.abdellah_el_moutaouakil.bookingservice.dtos.BookingResponseDTO;
import ma.abdellah_el_moutaouakil.bookingservice.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class BookingController {
    @Autowired
    private BookingService bookingService;
    @PostMapping(consumes = "application/json",produces="application/json",path = "/booking")
    public BookingResponseDTO createBooking(@RequestBody BookingRequestDTO bookingDTO){
        return bookingService.createBooking(bookingDTO);
    }
}
