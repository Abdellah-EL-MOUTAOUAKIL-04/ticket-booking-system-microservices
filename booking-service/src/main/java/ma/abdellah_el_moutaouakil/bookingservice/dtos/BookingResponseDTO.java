package ma.abdellah_el_moutaouakil.bookingservice.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookingResponseDTO {
    private Long userId;
    private Long eventId;
    private Long ticketCount;
    private Double totalPrice;
}
