package com.nuri.pangea.service.mapper;


import com.nuri.pangea.domain.*;
import com.nuri.pangea.service.dto.DealDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Deal} and its DTO {@link DealDTO}.
 */
@Mapper(componentModel = "spring", uses = {IssueMapper.class, PackMapper.class, AvatarMapper.class})
public interface DealMapper extends EntityMapper<DealDTO, Deal> {

    @Mapping(source = "with.id", target = "withId")
    @Mapping(source = "pack.id", target = "packId")
    @Mapping(source = "to.id", target = "toId")
    DealDTO toDto(Deal deal);

    @Mapping(target = "deals", ignore = true)
    @Mapping(target = "removeDeal", ignore = true)
    @Mapping(source = "withId", target = "with")
    @Mapping(source = "packId", target = "pack")
    @Mapping(source = "toId", target = "to")
    Deal toEntity(DealDTO dealDTO);

    default Deal fromId(Long id) {
        if (id == null) {
            return null;
        }
        Deal deal = new Deal();
        deal.setId(id);
        return deal;
    }
}
