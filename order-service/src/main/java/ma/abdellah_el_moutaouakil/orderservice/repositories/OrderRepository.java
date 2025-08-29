package ma.abdellah_el_moutaouakil.orderservice.repositories;

import ma.abdellah_el_moutaouakil.orderservice.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
}
