package net.haloz.mapper;

import net.haloz.dto.OwnerDto;
import net.haloz.entity.Owner;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OwnerMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "cats", ignore = true)
    Owner toOwner(OwnerDto ownerDto);
    OwnerDto toOwnerDto(Owner owner);
}
