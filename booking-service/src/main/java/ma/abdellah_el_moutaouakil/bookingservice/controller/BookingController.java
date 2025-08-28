package ma.abdellah_el_moutaouakil.bookingservice.controller;

import ma.abdellah_el_moutaouakil.bookingservice.dtos.BookingDTO;
import ma.abdellah_el_moutaouakil.bookingservice.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class BookingController {
    @Autowired
    private BookingService bookingService;
    @PostMapping
    public BookingDTO createBooking(BookingDTO bookingDTO){
        return bookingService.createBooking(bookingDTO);
    }
}
