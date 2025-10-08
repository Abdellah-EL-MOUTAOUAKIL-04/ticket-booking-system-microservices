package ma.abdellah_el_moutaouakil.inventoryservice.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Builder
@Data
@ToString
public class VenueDTO {
    private long id;
    @NotBlank(message = "Name is mandatory")
    private String name;
    @NotNull(message = "Total capacity is mandatory")
    private long totalCapacity;
    @NotBlank(message = "Address is mandatory")
    private String address;
    private boolean active;
}
