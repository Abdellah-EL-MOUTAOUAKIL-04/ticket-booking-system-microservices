package ma.abdellah_el_moutaouakil.bookingservice.repositories;

import ma.abdellah_el_moutaouakil.bookingservice.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
