package ma.abdellah_el_moutaouakil.inventoryservice.service;

import ma.abdellah_el_moutaouakil.inventoryservice.dtos.EventDTO;
import ma.abdellah_el_moutaouakil.inventoryservice.dtos.VenueDTO;

import java.util.List;

public interface InventoryService {
    public List<EventDTO> listEvents();

    EventDTO getEvent(long eventId);

    EventDTO saveEvent(EventDTO eventDTO);

    EventDTO updateEvent(EventDTO eventDTO);

    EventDTO updateEventCpacity(Long eventId, Long ticketsBooked);

    VenueDTO getVenue(long venueId);

    VenueDTO saveVenue(VenueDTO venueDTO);

    void deleteVenue(Long venueId);

    VenueDTO toggleVenue(Long venueId);

    List<VenueDTO> getAllVenue();

    VenueDTO updateVenue(VenueDTO venueDTO);
}
