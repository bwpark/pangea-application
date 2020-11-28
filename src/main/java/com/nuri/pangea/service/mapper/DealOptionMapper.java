package com.nuri.pangea.service.mapper;


import com.nuri.pangea.domain.*;
import com.nuri.pangea.service.dto.DealOptionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link DealOption} and its DTO {@link DealOptionDTO}.
 */
@Mapper(componentModel = "spring", uses = {DealMapper.class})
public interface DealOptionMapper extends EntityMapper<DealOptionDTO, DealOption> {

    @Mapping(source = "pack.id", target = "packId")
    DealOptionDTO toDto(DealOption dealOption);

    @Mapping(source = "packId", target = "pack")
    DealOption toEntity(DealOptionDTO dealOptionDTO);

    default DealOption fromId(Long id) {
        if (id == null) {
            return null;
        }
        DealOption dealOption = new DealOption();
        dealOption.setId(id);
        return dealOption;
    }
}
