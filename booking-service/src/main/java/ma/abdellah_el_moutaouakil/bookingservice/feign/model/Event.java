package ma.abdellah_el_moutaouakil.bookingservice.feign.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Event {
    private Long id;
    private String name;
    private Long leftCapacity;
    private Double ticketPrice;
    private Venue venue;
}
