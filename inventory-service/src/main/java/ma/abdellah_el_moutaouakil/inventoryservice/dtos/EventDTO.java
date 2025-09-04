package ma.abdellah_el_moutaouakil.inventoryservice.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class EventDTO {
    private Long id;
    @NotBlank(message = "Name is mandatory")
    private String name;
    @NotNull(message = "Left capacity is mandatory")
    private Long leftCapacity;
    @NotNull(message = "Ticket price is mandatory")
    private Double ticketPrice;
    private VenueDTO venue;
}
