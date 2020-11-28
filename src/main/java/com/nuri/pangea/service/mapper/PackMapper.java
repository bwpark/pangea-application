package com.nuri.pangea.service.mapper;


import com.nuri.pangea.domain.*;
import com.nuri.pangea.service.dto.PackDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Pack} and its DTO {@link PackDTO}.
 */
@Mapper(componentModel = "spring", uses = {AvatarMapper.class})
public interface PackMapper extends EntityMapper<PackDTO, Pack> {

    @Mapping(source = "me.id", target = "meId")
    PackDTO toDto(Pack pack);

    @Mapping(target = "deals", ignore = true)
    @Mapping(target = "removeDeal", ignore = true)
    @Mapping(source = "meId", target = "me")
    Pack toEntity(PackDTO packDTO);

    default Pack fromId(Long id) {
        if (id == null) {
            return null;
        }
        Pack pack = new Pack();
        pack.setId(id);
        return pack;
    }
}
