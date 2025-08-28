package ma.abdellah_el_moutaouakil.inventoryservice.dtos;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class EventDTO {
    private Long id;
    private String name;
    private Long leftCapacity;
    private Double ticketPrice;
    private VenueDTO venue;
}
