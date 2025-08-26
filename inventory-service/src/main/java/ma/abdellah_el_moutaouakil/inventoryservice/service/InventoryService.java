package ma.abdellah_el_moutaouakil.inventoryservice.service;

import ma.abdellah_el_moutaouakil.inventoryservice.dtos.EventDTO;
import ma.abdellah_el_moutaouakil.inventoryservice.dtos.VenueDTO;

import java.util.List;
import java.util.Optional;

public interface InventoryService {
    public List<EventDTO> listEvents();

    Optional<EventDTO> getEvent(long eventId);

    Optional<VenueDTO> getVenue(long venueId);
}
