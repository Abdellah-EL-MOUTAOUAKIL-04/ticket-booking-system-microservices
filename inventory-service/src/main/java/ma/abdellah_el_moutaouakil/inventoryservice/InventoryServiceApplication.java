package ma.abdellah_el_moutaouakil.inventoryservice;

import ma.abdellah_el_moutaouakil.inventoryservice.entities.Event;
import ma.abdellah_el_moutaouakil.inventoryservice.entities.Venue;
import ma.abdellah_el_moutaouakil.inventoryservice.repositories.EventRepository;
import ma.abdellah_el_moutaouakil.inventoryservice.repositories.VenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.UUID;

@SpringBootApplication
public class InventoryServiceApplication {
    @Autowired
    EventRepository eventRepository;
    @Autowired
    VenueRepository venueRepository;
    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(){
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                Venue venue1 = Venue.builder()
                        .name("Grand Olympic Stadium")
                        .totalCapacity(80000L)
                        .address("123 Sports Avenue, Metropolis")
                        .build();

                Venue venue2 = Venue.builder()
                        .name("Royal Concert Hall")
                        .totalCapacity(2500L)
                        .address("456 Music Street, Downtown")
                        .build();

                Venue venue3 = Venue.builder()
                        .name("Tech Innovation Center")
                        .totalCapacity(1200L)
                        .address("789 Innovation Blvd, Tech Park")
                        .build();

                venueRepository.save(venue1);
                venueRepository.save(venue2);
                venueRepository.save(venue3);

                Event event1 = Event.builder()
                        .name("Summer Music Festival 2024")
                        .totalCapacity(75000L)
                        .leftCapacity(15000L)
                        .venue(venue1)
                        .build();

                Event event2 = Event.builder()
                        .name("Mozart Symphony Night")
                        .totalCapacity(2000L)
                        .leftCapacity(250L)
                        .venue(venue2)
                        .build();

                Event event3 = Event.builder()
                        .name("AI & Machine Learning Summit")
                        .totalCapacity(1000L)
                        .leftCapacity(0L)
                        .venue(venue3)
                        .build();
                eventRepository.save(event1);
                eventRepository.save(event2);
                eventRepository.save(event3);
            }
        };
    }
}
