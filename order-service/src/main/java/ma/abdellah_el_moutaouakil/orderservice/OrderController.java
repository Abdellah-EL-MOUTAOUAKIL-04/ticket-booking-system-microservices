package ma.abdellah_el_moutaouakil.orderservice;

import ma.abdellah_el_moutaouakil.orderservice.entities.Order;
import ma.abdellah_el_moutaouakil.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class OrderController {
    @Autowired
    OrderService orderService;
    @GetMapping("/bookings")
    public List<Order> getBookings(){
        return orderService.findAllBookings();
    }
}
