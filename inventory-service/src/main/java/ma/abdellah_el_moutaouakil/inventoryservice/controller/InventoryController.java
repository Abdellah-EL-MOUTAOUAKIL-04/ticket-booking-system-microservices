package ma.abdellah_el_moutaouakil.inventoryservice.controller;

import jakarta.validation.Valid;
import ma.abdellah_el_moutaouakil.inventoryservice.dtos.EventDTO;
import ma.abdellah_el_moutaouakil.inventoryservice.dtos.VenueDTO;
import ma.abdellah_el_moutaouakil.inventoryservice.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

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
    public ResponseEntity<EventDTO> getEvent(@PathVariable long eventId){
        EventDTO eventDTO=inventoryService.getEvent(eventId);
        return ResponseEntity.ok(eventDTO);
    }

    @PostMapping("/inventory/events")
    public ResponseEntity<EventDTO> addEvent(@Valid @RequestBody EventDTO eventDTO){
        EventDTO createdEvent=inventoryService.saveEvent(eventDTO);
        return ResponseEntity.created(URI.create("/inventory/events/"+createdEvent.getId())).body(createdEvent);
    }

    @PutMapping("/inventory/events")
    public ResponseEntity<EventDTO> updateEvent(@Valid @RequestBody EventDTO eventDTO){
        EventDTO eventDTO1=inventoryService.updateEvent(eventDTO);
        return ResponseEntity.ok(eventDTO1);
    }

    @PatchMapping("/inventory/event/{eventId}/capacity")
    public ResponseEntity<EventDTO> updateEventCapacity(@PathVariable("eventId") Long eventId,@RequestParam("capacity") Long ticketsBooked){
        EventDTO eventDTO1=inventoryService.updateEventCpacity(eventId,ticketsBooked);
        return ResponseEntity.ok(eventDTO1);
    }

    @GetMapping("/inventory/venue/{venueId}")
    public ResponseEntity<VenueDTO> getVenue(@PathVariable long venueId){
        VenueDTO venueDTO=inventoryService.getVenue(venueId);
        return ResponseEntity.ok(venueDTO);
    }

    @GetMapping("/inventory/venue")
    public ResponseEntity<List<VenueDTO>> getAllVenue(){
        List<VenueDTO> venueDTOS= inventoryService.getAllVenue();
        return ResponseEntity.ok(venueDTOS);
    }

    @PostMapping("/inventory/venue")
    public ResponseEntity<VenueDTO> createVenue(@Valid @RequestBody VenueDTO venueDTO){
        VenueDTO venueDTO1=inventoryService.saveVenue(venueDTO);
        return ResponseEntity.created(URI.create("/inventory/venue/"+venueDTO1.getId())).body(venueDTO1);
    }


    @PutMapping("/inventory/venue")
    public ResponseEntity<VenueDTO> updateVenue(@Valid @RequestBody VenueDTO venueDTO){
        VenueDTO venueDTO1= inventoryService.updateVenue(venueDTO);
        return ResponseEntity.ok(venueDTO1);
    }

    @DeleteMapping("/inventory/venue/{venueId}")
    public ResponseEntity<Void> removeVenue(@PathVariable Long venueId){
        inventoryService.deleteVenue(venueId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/inventory/venue/{venueId}/toggle")
    public ResponseEntity<VenueDTO> toggleVenue(@PathVariable Long venueId){
        VenueDTO venueDTO=inventoryService.toggleVenue(venueId);
        return ResponseEntity.ok(venueDTO);
    }
}
