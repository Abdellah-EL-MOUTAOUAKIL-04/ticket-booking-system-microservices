package ma.abdellah_el_moutaouakil.inventoryservice.controller;

import ma.abdellah_el_moutaouakil.inventoryservice.dtos.EventDTO;
import ma.abdellah_el_moutaouakil.inventoryservice.dtos.VenueDTO;
import ma.abdellah_el_moutaouakil.inventoryservice.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class InventoryController {
    @Autowired
    InventoryService inventoryService;
    @GetMapping("/inventory/events")
    public List<EventDTO> getAllEvents(){
        return inventoryService.listEvents();
    }
    @GetMapping("/inventory/events/{eventId}")
    public Optional<EventDTO> getEvent(@PathVariable long eventId){
        return inventoryService.getEvent(eventId);
    }
    @GetMapping("/inventory/venue/{venueId}")
    public Optional<VenueDTO> getVenue(@PathVariable long venueId){
        return inventoryService.getVenue(venueId);
    }
    @PutMapping("/inventory/event/{eventId}/capacity/{capacity}")
    public ResponseEntity<Void> updateEventCapacity(@PathVariable("eventId") Long eventId,@PathVariable("capacity") Long ticketsBooked){
        inventoryService.updateEventCpacity(eventId,ticketsBooked);
        return ResponseEntity.ok().build();
    }
}
