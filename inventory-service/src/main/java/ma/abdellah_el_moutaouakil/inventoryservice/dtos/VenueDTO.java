package ma.abdellah_el_moutaouakil.inventoryservice.dtos;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class VenueDTO {
    private String name;
    private long totalCapacity;
    private String address;
}
