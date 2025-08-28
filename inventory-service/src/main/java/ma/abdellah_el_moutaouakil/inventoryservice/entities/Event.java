package ma.abdellah_el_moutaouakil.inventoryservice.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Getter
public class Event {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long totalCapacity;
    private Long leftCapacity;
    private Double ticketPrice;
    @ManyToOne
    @JoinColumn(name = "venue_id")
    private Venue venue;
}
