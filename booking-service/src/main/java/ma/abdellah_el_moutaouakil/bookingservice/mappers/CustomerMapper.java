package ma.abdellah_el_moutaouakil.bookingservice.mappers;

import ma.abdellah_el_moutaouakil.bookingservice.dtos.CustomerDTO;
import ma.abdellah_el_moutaouakil.bookingservice.entities.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerMapper INSTANCE= Mappers.getMapper(CustomerMapper.class);
    CustomerDTO toDTO(Customer customer);
    Customer fromDTO(CustomerDTO customerDTO);
}
