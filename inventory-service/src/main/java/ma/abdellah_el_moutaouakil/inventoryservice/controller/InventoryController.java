package ma.abdellah_el_moutaouakil.inventoryservice.controller;

import ma.abdellah_el_moutaouakil.inventoryservice.dtos.EventDTO;
import ma.abdellah_el_moutaouakil.inventoryservice.dtos.VenueDTO;
import ma.abdellah_el_moutaouakil.inventoryservice.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
