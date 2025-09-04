package ma.abdellah_el_moutaouakil.inventoryservice.dtos;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.sql.Timestamp;
import java.util.List;

@Builder
@Data
public class ErrorResponseDTO {
    private String message;
    private List<String> details;
    private HttpStatus status;
    private Timestamp timestamp;
}
