package ma.abdellah_el_moutaouakil.inventoryservice.repositories;

import ma.abdellah_el_moutaouakil.inventoryservice.entities.Venue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VenueRepository extends JpaRepository<Venue,Long> {
}
