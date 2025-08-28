package ma.abdellah_el_moutaouakil.bookingservice.service;

import ma.abdellah_el_moutaouakil.bookingservice.dtos.BookingDTO;
import ma.abdellah_el_moutaouakil.bookingservice.entities.Customer;
import ma.abdellah_el_moutaouakil.bookingservice.feign.InventoryRestClient;
import ma.abdellah_el_moutaouakil.bookingservice.feign.model.Event;
import ma.abdellah_el_moutaouakil.bookingservice.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private InventoryRestClient inventoryRestClient;
    @Override
    public BookingDTO createBooking(BookingDTO bookingDTO) {
        Customer customer=customerRepository.findById(bookingDTO.getUserId()).orElse(null);
        if(customer==null){
            throw new RuntimeException("User Not Found");
        }
        Event event=inventoryRestClient.findEventById(bookingDTO.getEventId());
        System.out.println("Event response : "+event);
        if(event==null){
            throw new RuntimeException("Event Not Found");
        }
        return null;
    }
}
