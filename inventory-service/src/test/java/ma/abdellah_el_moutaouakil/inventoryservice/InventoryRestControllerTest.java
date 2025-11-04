package ma.abdellah_el_moutaouakil.inventoryservice;

import ma.abdellah_el_moutaouakil.inventoryservice.controller.InventoryController;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import ma.abdellah_el_moutaouakil.inventoryservice.dtos.EventDTO;
import ma.abdellah_el_moutaouakil.inventoryservice.dtos.VenueDTO;
import ma.abdellah_el_moutaouakil.inventoryservice.service.InventoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@WebMvcTest(InventoryController.class)
public class InventoryRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InventoryService inventoryService;

    @Autowired
    private ObjectMapper objectMapper;

    private EventDTO eventDTO;
    private VenueDTO venueDTO;

    @BeforeEach
    void setUp() {
        eventDTO = new EventDTO();
        eventDTO.setId(1L);
        eventDTO.setName("Concert Test");
        eventDTO.setLeftCapacity(100L);

        venueDTO = new VenueDTO();
        venueDTO.setId(1L);
        venueDTO.setName("Venue A");
        venueDTO.setActive(true);
    }

    @Test
    void shouldReturnAllEvents() throws Exception {
        Mockito.when(inventoryService.listEvents()).thenReturn(List.of(eventDTO));

        mockMvc.perform(get("/api/inventory/events"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("Concert Test")));
    }

    @Test
    void shouldReturnEventById() throws Exception {
        Mockito.when(inventoryService.getEvent(1L)).thenReturn(eventDTO);

        mockMvc.perform(get("/api/inventory/events/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Concert Test")));
    }

    @Test
    void shouldCreateEvent() throws Exception {
        Mockito.when(inventoryService.saveEvent(any(EventDTO.class))).thenReturn(eventDTO);

        mockMvc.perform(post("/api/inventory/events")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(eventDTO)))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", containsString("/inventory/events/1")))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Concert Test")));
    }

    @Test
    void shouldUpdateEvent() throws Exception {
        Mockito.when(inventoryService.updateEvent(any(EventDTO.class))).thenReturn(eventDTO);

        mockMvc.perform(put("/api/inventory/events/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(eventDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Concert Test")));
    }

    @Test
    void shouldUpdateEventCapacity() throws Exception {
        Mockito.when(inventoryService.updateEventCpacity(1L, 10L)).thenReturn(eventDTO);

        mockMvc.perform(patch("/api/inventory/event/1/capacity")
                        .param("capacity", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)));
    }

    @Test
    void shouldReturnVenueById() throws Exception {
        Mockito.when(inventoryService.getVenue(1L)).thenReturn(venueDTO);

        mockMvc.perform(get("/api/inventory/venue/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Venue A")));
    }

    @Test
    void shouldReturnAllVenues() throws Exception {
        Mockito.when(inventoryService.getAllVenue()).thenReturn(List.of(venueDTO));

        mockMvc.perform(get("/api/inventory/venue"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].isActive", is(true)));
    }

    @Test
    void shouldCreateVenue() throws Exception {
        Mockito.when(inventoryService.saveVenue(any(VenueDTO.class))).thenReturn(venueDTO);

        mockMvc.perform(post("/api/inventory/venue")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(venueDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Venue A")));
    }

    @Test
    void shouldUpdateVenue() throws Exception {
        Mockito.when(inventoryService.updateVenue(any(VenueDTO.class))).thenReturn(venueDTO);

        mockMvc.perform(put("/api/inventory/venue/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(venueDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Venue A")));
    }

    @Test
    void shouldDeleteVenue() throws Exception {
        mockMvc.perform(delete("/api/inventory/venue/1"))
                .andExpect(status().isOk());

        Mockito.verify(inventoryService).deleteVenue(1L);
    }

    @Test
    void shouldToggleVenue() throws Exception {
        Mockito.when(inventoryService.toggleVenue(1L)).thenReturn(venueDTO);

        mockMvc.perform(patch("/api/inventory/venue/1/toggle"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.isActive", is(true)));
    }
}
