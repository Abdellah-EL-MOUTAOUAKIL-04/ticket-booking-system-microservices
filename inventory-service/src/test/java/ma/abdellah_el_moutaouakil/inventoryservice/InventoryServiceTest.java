package ma.abdellah_el_moutaouakil.inventoryservice;

import ma.abdellah_el_moutaouakil.inventoryservice.dtos.EventDTO;
import ma.abdellah_el_moutaouakil.inventoryservice.dtos.VenueDTO;
import ma.abdellah_el_moutaouakil.inventoryservice.entities.Event;
import ma.abdellah_el_moutaouakil.inventoryservice.entities.Venue;
import ma.abdellah_el_moutaouakil.inventoryservice.exceptions.ResourceNotFoundException;
import ma.abdellah_el_moutaouakil.inventoryservice.mappers.EventMapper;
import ma.abdellah_el_moutaouakil.inventoryservice.mappers.VenueMapper;
import ma.abdellah_el_moutaouakil.inventoryservice.repositories.EventRepository;
import ma.abdellah_el_moutaouakil.inventoryservice.repositories.VenueRepository;
import ma.abdellah_el_moutaouakil.inventoryservice.service.InventoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InventoryServiceTest {

    @Mock
    private EventRepository eventRepository;
    @Mock
    private VenueRepository venueRepository;
    @Mock
    private EventMapper eventMapper;
    @Mock
    private VenueMapper venueMapper;

    @InjectMocks
    private InventoryService inventoryService;

    private Event event;
    private EventDTO eventDTO;
    private Venue venue;
    private VenueDTO venueDTO;

    @BeforeEach
    void setUp() {
        event = new Event();
        event.setId(1L);
        event.setName("Concert Test");
        event.setLeftCapacity(100L);

        eventDTO = new EventDTO();
        eventDTO.setId(1L);
        eventDTO.setName("Concert Test");
        eventDTO.setLeftCapacity(100L);

        venue = new Venue();
        venue.setId(1L);
        venue.setName("Venue A");
        venue.setIsActive(true);

        venueDTO = new VenueDTO();
        venueDTO.setId(1L);
        venueDTO.setName("Venue A");
        venueDTO.setActive(true);
    }

    @Test
    void shouldListAllEvents() {
        when(eventRepository.findAll()).thenReturn(List.of(event));
        when(eventMapper.toDTO(event)).thenReturn(eventDTO);

        List<EventDTO> result = inventoryService.listEvents();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getName()).isEqualTo("Concert Test");
        verify(eventRepository, times(1)).findAll();
    }

    @Test
    void shouldReturnEventById() {
        when(eventRepository.findById(1L)).thenReturn(Optional.of(event));
        when(eventMapper.toDTO(event)).thenReturn(eventDTO);

        EventDTO result = inventoryService.getEvent(1L);

        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getName()).isEqualTo("Concert Test");
    }

    @Test
    void shouldThrowExceptionWhenEventNotFound() {
        when(eventRepository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> inventoryService.getEvent(1L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("No event exist with this id");
    }

    @Test
    void shouldSaveEvent() {
        when(eventMapper.fromDTO(eventDTO)).thenReturn(event);
        when(eventRepository.save(event)).thenReturn(event);
        when(eventMapper.toDTO(event)).thenReturn(eventDTO);

        EventDTO result = inventoryService.saveEvent(eventDTO);

        assertThat(result.getId()).isEqualTo(1L);
        verify(eventRepository, times(1)).save(any(Event.class));
    }

    @Test
    void shouldUpdateEventCapacity() {
        when(eventRepository.findById(1L)).thenReturn(Optional.of(event));
        when(eventRepository.saveAndFlush(any(Event.class))).thenReturn(event);
        when(eventMapper.toDTO(event)).thenReturn(eventDTO);

        EventDTO result = inventoryService.updateEventCpacity(1L, 20L);

        assertThat(result.getLeftCapacity()).isEqualTo(100L - 20L);
        verify(eventRepository).saveAndFlush(any(Event.class));
    }

    @Test
    void shouldToggleVenueStatus() {
        when(venueRepository.findById(1L)).thenReturn(Optional.of(venue));
        when(venueRepository.saveAndFlush(any(Venue.class))).thenReturn(venue);
        when(venueMapper.toDTO(any(Venue.class))).thenReturn(venueDTO);

        VenueDTO result = inventoryService.toggleVenue(1L);

        verify(venueRepository).findById(1L);
        verify(venueRepository).saveAndFlush(any(Venue.class));
        assertThat(result.getId()).isEqualTo(1L);
    }

    @Test
    void shouldThrowExceptionWhenTogglingNonExistingVenue() {
        when(venueRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> inventoryService.toggleVenue(99L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("No venue exist with this id");
    }
}
