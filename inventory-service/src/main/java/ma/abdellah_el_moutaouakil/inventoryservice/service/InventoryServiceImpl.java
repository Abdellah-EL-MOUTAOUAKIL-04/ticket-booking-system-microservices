package ma.abdellah_el_moutaouakil.inventoryservice.service;

import jakarta.transaction.Transactional;
import ma.abdellah_el_moutaouakil.inventoryservice.dtos.EventDTO;
import ma.abdellah_el_moutaouakil.inventoryservice.dtos.VenueDTO;
import ma.abdellah_el_moutaouakil.inventoryservice.entities.Event;
import ma.abdellah_el_moutaouakil.inventoryservice.mappers.EventMapper;
import ma.abdellah_el_moutaouakil.inventoryservice.mappers.VenueMapper;
import ma.abdellah_el_moutaouakil.inventoryservice.repositories.EventRepository;
import ma.abdellah_el_moutaouakil.inventoryservice.repositories.VenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class InventoryServiceImpl implements InventoryService{
    @Autowired
    EventRepository eventRepository;
    @Autowired
    private VenueRepository venueRepository;
    @Autowired
    private EventMapper eventMapper;
    @Autowired
    private VenueMapper venueMapper;

    @Override
    public List<EventDTO> listEvents() {
        return eventRepository.findAll().stream().map(eventMapper::toDTO).toList();
    }

    @Override
    public Optional<EventDTO> getEvent(long eventId) {
        return eventRepository.findById(eventId).map(eventMapper::toDTO);
    }

    @Override
    public Optional<VenueDTO> getVenue(long venueId) {
        return venueRepository.findById(venueId).map(venueMapper::toDTO);
    }
}
