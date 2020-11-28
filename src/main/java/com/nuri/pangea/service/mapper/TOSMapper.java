package com.nuri.pangea.service.mapper;


import com.nuri.pangea.domain.*;
import com.nuri.pangea.service.dto.TOSDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link TOS} and its DTO {@link TOSDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TOSMapper extends EntityMapper<TOSDTO, TOS> {



    default TOS fromId(Long id) {
        if (id == null) {
            return null;
        }
        TOS tOS = new TOS();
        tOS.setId(id);
        return tOS;
    }
}
