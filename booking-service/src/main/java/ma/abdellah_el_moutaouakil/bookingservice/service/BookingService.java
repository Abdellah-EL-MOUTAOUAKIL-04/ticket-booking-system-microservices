package ma.abdellah_el_moutaouakil.bookingservice.service;

import ma.abdellah_el_moutaouakil.bookingservice.dtos.BookingDTO;

public interface BookingService{
    BookingDTO createBooking(BookingDTO bookingDTO);
}
