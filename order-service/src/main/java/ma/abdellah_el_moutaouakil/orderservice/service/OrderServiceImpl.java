package ma.abdellah_el_moutaouakil.orderservice.service;

import lombok.extern.slf4j.Slf4j;
import ma.abdellah_el_moutaouakil.orderservice.entities.Order;
import ma.abdellah_el_moutaouakil.bookingservice.event.BookingEvent;
import ma.abdellah_el_moutaouakil.orderservice.feign.InventoryRestClient;
import ma.abdellah_el_moutaouakil.orderservice.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService{
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    InventoryRestClient inventoryRestClient;

    @KafkaListener(topics = "booking",groupId = "order-service")
    public void orderEvent(BookingEvent bookingEvent){
        //log.info("Received order event: {}",bookingEvent);

        Order order=createOrder(bookingEvent);

        orderRepository.saveAndFlush(order);

        inventoryRestClient.updateInventory(order.getEventId(),order.getTicketCount());

        //log.info("Inventory updated for event : {}, less tickets: {}",order.getEventId(),order.getTicketCount());
    }
    private Order createOrder(BookingEvent bookingEvent){
        return Order.builder()
                .cutsomerId(bookingEvent.getUserId())
                .eventId(bookingEvent.getEventId())
                .ticketCount(bookingEvent.getTicketCount())
                .totalPrice(bookingEvent.getTotalPrice())
                .build();
    }

    @Override
    public List<Order> findAllBookings() {
        return orderRepository.findAll();
    }
}
