package net.haloz.mapper;

import net.haloz.dto.CatDto;
import net.haloz.entity.Cat;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CatMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "owner", ignore = true)
    @Mapping(target = "friends", ignore = true)
    Cat toCat(CatDto catDto);
    CatDto toCatDto(Cat cat);
}
