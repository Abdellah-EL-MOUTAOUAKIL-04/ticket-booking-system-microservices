package ma.abdellah_el_moutaouakil.orderservice.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name="`order`")
public class Order {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "total")
    private Double totalPrice;
    @Column(name = "quantity")
    private Long ticketCount;
    @CreationTimestamp
    @Column(name = "placed_at",updatable = false,nullable = false)
    private LocalDateTime placedAt;
    @Column(name = "customer_id")
    private Long cutsomerId;
    @Column(name = "event_id")
    private Long eventId;
}
