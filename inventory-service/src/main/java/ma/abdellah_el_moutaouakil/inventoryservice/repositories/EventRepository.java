package ma.abdellah_el_moutaouakil.inventoryservice.repositories;

import ma.abdellah_el_moutaouakil.inventoryservice.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event,Long> {
}
