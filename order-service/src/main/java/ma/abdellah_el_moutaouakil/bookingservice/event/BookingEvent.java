package ma.abdellah_el_moutaouakil.bookingservice.event;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class BookingEvent {
    private Long userId;
    private Long eventId;
    private Long ticketCount;
    private Double totalPrice;
}
