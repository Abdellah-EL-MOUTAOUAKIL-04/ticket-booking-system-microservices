package ma.abdellah_el_moutaouakil.bookingservice.service;

import ma.abdellah_el_moutaouakil.bookingservice.dtos.BookingRequestDTO;
import ma.abdellah_el_moutaouakil.bookingservice.dtos.BookingResponseDTO;
import org.springframework.stereotype.Service;

@Service
public interface BookingService{
    BookingResponseDTO createBooking(BookingRequestDTO bookingDTO);
}
