package com.nuri.pangea.service.mapper;


import com.nuri.pangea.domain.*;
import com.nuri.pangea.service.dto.RegularDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Regular} and its DTO {@link RegularDTO}.
 */
@Mapper(componentModel = "spring", uses = {AvatarMapper.class})
public interface RegularMapper extends EntityMapper<RegularDTO, Regular> {

    @Mapping(source = "you.id", target = "youId")
    @Mapping(source = "me.id", target = "meId")
    RegularDTO toDto(Regular regular);

    @Mapping(source = "youId", target = "you")
    @Mapping(source = "meId", target = "me")
    Regular toEntity(RegularDTO regularDTO);

    default Regular fromId(Long id) {
        if (id == null) {
            return null;
        }
        Regular regular = new Regular();
        regular.setId(id);
        return regular;
    }
}
