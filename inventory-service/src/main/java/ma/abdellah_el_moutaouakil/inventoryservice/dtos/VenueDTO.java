package ma.abdellah_el_moutaouakil.inventoryservice.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@Data
@ToString @NoArgsConstructor @AllArgsConstructor
public class VenueDTO {
    private long id;
    private String name;
    private long totalCapacity;
    private String address;
    private boolean active;
}
