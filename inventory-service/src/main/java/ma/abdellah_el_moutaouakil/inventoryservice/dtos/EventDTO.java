package ma.abdellah_el_moutaouakil.inventoryservice.dtos;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class EventDTO {
    private Long eventId;
    private String name;
    private Long totalCapacity;
    private Long ticketPrice;
    private VenueDTO venue;
}
