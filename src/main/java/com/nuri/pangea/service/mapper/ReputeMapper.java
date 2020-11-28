package com.nuri.pangea.service.mapper;


import com.nuri.pangea.domain.*;
import com.nuri.pangea.service.dto.ReputeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Repute} and its DTO {@link ReputeDTO}.
 */
@Mapper(componentModel = "spring", uses = {AvatarMapper.class})
public interface ReputeMapper extends EntityMapper<ReputeDTO, Repute> {

    @Mapping(source = "you.id", target = "youId")
    @Mapping(source = "me.id", target = "meId")
    ReputeDTO toDto(Repute repute);

    @Mapping(source = "youId", target = "you")
    @Mapping(source = "meId", target = "me")
    Repute toEntity(ReputeDTO reputeDTO);

    default Repute fromId(Long id) {
        if (id == null) {
            return null;
        }
        Repute repute = new Repute();
        repute.setId(id);
        return repute;
    }
}
