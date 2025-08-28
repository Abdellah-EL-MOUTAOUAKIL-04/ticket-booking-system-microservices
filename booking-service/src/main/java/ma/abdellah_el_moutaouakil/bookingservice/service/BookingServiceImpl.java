package ma.abdellah_el_moutaouakil.bookingservice.service;

import lombok.extern.slf4j.Slf4j;
import ma.abdellah_el_moutaouakil.bookingservice.dtos.BookingRequestDTO;
import ma.abdellah_el_moutaouakil.bookingservice.dtos.BookingResponseDTO;
import ma.abdellah_el_moutaouakil.bookingservice.entities.Customer;
import ma.abdellah_el_moutaouakil.bookingservice.event.BookingEvent;
import ma.abdellah_el_moutaouakil.bookingservice.feign.InventoryRestClient;
import ma.abdellah_el_moutaouakil.bookingservice.feign.model.Event;
import ma.abdellah_el_moutaouakil.bookingservice.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BookingServiceImpl implements BookingService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private InventoryRestClient inventoryRestClient;
    @Autowired
    private KafkaTemplate<String,BookingEvent> kafkaTemplate;

    @Override
    public BookingResponseDTO createBooking(BookingRequestDTO bookingRequestDTO) {
        Customer customer=customerRepository.findById(bookingRequestDTO.getUserId()).orElse(null);
        if(customer==null){
            throw new RuntimeException("User Not Found");
        }

        Event event=inventoryRestClient.findEventById(bookingRequestDTO.getEventId());
        if(event==null){
            throw new RuntimeException("Event Not Found");
        }
        log.info("Inventory Response: {}",event);
        if(event.getLeftCapacity()<bookingRequestDTO.getTicketCount()){
            throw new RuntimeException("Not enough inventory");
        }

        BookingEvent bookingEvent=createBookingEvent(bookingRequestDTO,customer,event);

        kafkaTemplate.send("booking",bookingEvent);
        log.info("Booking sent to Kafka : {}",bookingEvent);
        return null;
    }

    private BookingEvent createBookingEvent(BookingRequestDTO bookingRequestDTO, Customer customer, Event event) {
        return BookingEvent.builder()
                .userId(customer.getId())
                .eventId(bookingRequestDTO.getEventId())
                .ticketCount(bookingRequestDTO.getTicketCount())
                .totalPrice(bookingRequestDTO.getTicketCount()* event.getTicketPrice())
                .build();
    }
}
