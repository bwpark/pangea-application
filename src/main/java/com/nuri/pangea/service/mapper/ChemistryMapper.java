package com.nuri.pangea.service.mapper;


import com.nuri.pangea.domain.*;
import com.nuri.pangea.service.dto.ChemistryDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Chemistry} and its DTO {@link ChemistryDTO}.
 */
@Mapper(componentModel = "spring", uses = {AvatarMapper.class})
public interface ChemistryMapper extends EntityMapper<ChemistryDTO, Chemistry> {

    @Mapping(source = "you.id", target = "youId")
    @Mapping(source = "me.id", target = "meId")
    ChemistryDTO toDto(Chemistry chemistry);

    @Mapping(source = "youId", target = "you")
    @Mapping(source = "meId", target = "me")
    Chemistry toEntity(ChemistryDTO chemistryDTO);

    default Chemistry fromId(Long id) {
        if (id == null) {
            return null;
        }
        Chemistry chemistry = new Chemistry();
        chemistry.setId(id);
        return chemistry;
    }
}
