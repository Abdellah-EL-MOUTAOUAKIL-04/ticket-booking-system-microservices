package ma.abdellah_el_moutaouakil.inventoryservice.mappers;

import ma.abdellah_el_moutaouakil.inventoryservice.dtos.VenueDTO;
import ma.abdellah_el_moutaouakil.inventoryservice.entities.Venue;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface VenueMapper {
    VenueMapper INSTANCE = Mappers.getMapper(VenueMapper.class);
    VenueDTO toDTO(Venue venue);

    @Mapping(source = "active", target = "isActive")
    Venue fromDTO(VenueDTO dto);
}
