package ma.abdellah_el_moutaouakil.bookingservice.feign;

import ma.abdellah_el_moutaouakil.bookingservice.feign.model.Event;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="inventory-service")
public interface InventoryRestClient {
    @GetMapping("/api/inventory/events/{eventId}")
    Event findEventById(@PathVariable Long eventId);
}
