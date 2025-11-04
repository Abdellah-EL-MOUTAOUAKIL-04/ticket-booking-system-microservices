package ma.abdellah_el_moutaouakil.inventoryservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data @NoArgsConstructor @AllArgsConstructor
public class EventDTO {
    private Long id;
    private String name;
    private Long leftCapacity;
    private Double ticketPrice;
    private VenueDTO venue;
}
