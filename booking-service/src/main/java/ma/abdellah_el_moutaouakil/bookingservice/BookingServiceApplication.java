package ma.abdellah_el_moutaouakil.bookingservice;

import ma.abdellah_el_moutaouakil.bookingservice.entities.Customer;
import ma.abdellah_el_moutaouakil.bookingservice.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients
public class BookingServiceApplication {

    @Autowired
    CustomerRepository customerRepository;

    public static void main(String[] args) {
        SpringApplication.run(BookingServiceApplication.class, args);
    }

    //@Bean
    CommandLineRunner commandLineRunner(){
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                Customer c1= Customer.builder().name("abdellah").email("abdellah@gmail.com").address("Agadir").build();
                Customer c2= Customer.builder().name("ahmed").email("ahmed@gmail.com").address("Agadir").build();
                customerRepository.save(c1);
                customerRepository.save(c2);
            }
        };
    }
}
