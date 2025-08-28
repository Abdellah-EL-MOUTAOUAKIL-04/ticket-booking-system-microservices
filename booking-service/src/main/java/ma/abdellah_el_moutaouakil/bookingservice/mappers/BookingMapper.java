package ma.abdellah_el_moutaouakil.bookingservice.mappers;

import ma.abdellah_el_moutaouakil.bookingservice.dtos.BookingRequestDTO;
import ma.abdellah_el_moutaouakil.bookingservice.dtos.BookingResponseDTO;
import ma.abdellah_el_moutaouakil.bookingservice.entities.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BookingMapper {
    BookingMapper INSTANCE= Mappers.getMapper(BookingMapper.class);
    BookingResponseDTO toDTO(Booking booking);
    Booking fromDTO(BookingRequestDTO bookingRequestDTO);
}
