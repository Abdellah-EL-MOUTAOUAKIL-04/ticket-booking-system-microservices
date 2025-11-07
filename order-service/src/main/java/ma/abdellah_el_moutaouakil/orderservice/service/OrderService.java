package ma.abdellah_el_moutaouakil.orderservice.service;

import ma.abdellah_el_moutaouakil.orderservice.entities.Order;

import java.util.List;

public interface OrderService {
    List<Order> findAllBookings();
}
