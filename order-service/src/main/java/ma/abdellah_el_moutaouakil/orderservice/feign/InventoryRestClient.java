package ma.abdellah_el_moutaouakil.orderservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "inventory-service")
public interface InventoryRestClient {
    @PutMapping("/api/inventory/event/{eventId}/capacity/{capacity}")
    ResponseEntity<Void> updateInventory(@PathVariable Long eventId, @PathVariable Long capacity);
}
