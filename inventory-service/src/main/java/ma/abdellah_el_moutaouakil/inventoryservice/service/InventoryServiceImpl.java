package ma.abdellah_el_moutaouakil.inventoryservice.service;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import ma.abdellah_el_moutaouakil.inventoryservice.dtos.EventDTO;
import ma.abdellah_el_moutaouakil.inventoryservice.dtos.VenueDTO;
import ma.abdellah_el_moutaouakil.inventoryservice.entities.Event;
import ma.abdellah_el_moutaouakil.inventoryservice.entities.Venue;
import ma.abdellah_el_moutaouakil.inventoryservice.exceptions.ResourceNotFoundException;
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
@Slf4j
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
    public EventDTO getEvent(long eventId) {
        Optional<EventDTO> eventDTO= eventRepository.findById(eventId).map(eventMapper::toDTO);
        if(eventDTO.isEmpty())
            throw new ResourceNotFoundException("No event exist with this id:"+eventId);
        return eventDTO.get();
    }

    @Override
    public EventDTO saveEvent(EventDTO eventDTO) {
        Event event=eventRepository.save(eventMapper.fromDTO(eventDTO));
        return eventMapper.toDTO(event);
    }

    @Override
    public EventDTO updateEvent(EventDTO eventDTO) {
        Optional<Event> event=eventRepository.findById(eventDTO.getId());
        if(event.isEmpty())
                throw new ResourceNotFoundException("The event that you trying to update does not exist.");
        return eventMapper.toDTO(eventRepository.saveAndFlush(eventMapper.fromDTO(eventDTO)));
    }

    @Override
    public VenueDTO getVenue(long venueId) {
        Optional<VenueDTO> venueDTO= venueRepository.findById(venueId).map(venueMapper::toDTO);
        if (venueDTO.isEmpty())
            throw new ResourceNotFoundException("No venue exist with this id:"+venueId);
        return venueDTO.get();
    }

    @Override
    public EventDTO updateEventCpacity(Long eventId, Long ticketsBooked) {
        Optional<Event> event=eventRepository.findById(eventId);
        if(event.isEmpty())
            throw new ResourceNotFoundException("No event exist with this id:"+eventId);;
        Event event1=event.get();
        event1.setLeftCapacity(event1.getLeftCapacity()-ticketsBooked);
        event1=eventRepository.saveAndFlush(event1);
        log.info("Updated event capacity for event id: {} with tickets booked: {}",eventId,ticketsBooked);
        return eventMapper.toDTO(event1);
    }

    @Override
    public VenueDTO saveVenue(VenueDTO venueDTO) {
        Venue venue = venueRepository.save(venueMapper.fromDTO(venueDTO));
        return venueMapper.toDTO(venue);
    }

    @Override
    public void deleteVenue(Long venueId) {
        Optional<Venue> venue=venueRepository.findById(venueId);
        if(venue.isEmpty())
            throw new ResourceNotFoundException("No venue exist with this id:"+venueId);
        Venue venue1=venue.get();
        venueRepository.delete(venue1);
    }

    @Override
    public VenueDTO toggleVenue(Long venueId) {
        Optional<Venue> venue=venueRepository.findById(venueId);
        if(venue.isEmpty())
            throw new ResourceNotFoundException("No venue exist with this id:"+venueId);
        Venue venue1=venue.get();
        venue1.setIsActive(!venue1.isActive());
        Venue venue2=venueRepository.saveAndFlush(venue1);
        return venueMapper.toDTO(venue2);
    }

    @Override
    public List<VenueDTO> getAllVenue() {
        return venueRepository.findAll().stream().map(venueMapper::toDTO).toList();
    }

    @Override
    public VenueDTO updateVenue(VenueDTO venueDTO) {
        Optional<Venue> venue=venueRepository.findById(venueDTO.getId());
        if(venue.isEmpty())
            throw new ResourceNotFoundException("the venue that you are trying to update doesn't exist:"+venueDTO.getId());
        return venueMapper.toDTO(venueRepository.saveAndFlush(venueMapper.fromDTO(venueDTO)));
    }
}
