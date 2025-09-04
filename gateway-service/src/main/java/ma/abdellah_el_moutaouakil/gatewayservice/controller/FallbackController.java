package ma.abdellah_el_moutaouakil.gatewayservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fallback")
public class FallbackController {

    @GetMapping("/booking")
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public String bookingFallback() {
        return "Booking service is temporarily unavailable. Please try again later.";
    }

    @GetMapping("/order")
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public String orderFallback() {
        return "Order service is temporarily unavailable. Please try again later.";
    }

    @GetMapping("/inventory")
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public String inventoryFallback() {
        return "Inventory service is temporarily unavailable. Please try again later.";
    }

    @GetMapping("/uri")
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public String genericFallback() {
        return "Service is temporarily unavailable. Please try again later.";
    }
}