package com.nuri.pangea.service.mapper;


import com.nuri.pangea.domain.*;
import com.nuri.pangea.service.dto.InteractDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Interact} and its DTO {@link InteractDTO}.
 */
@Mapper(componentModel = "spring", uses = {AvatarMapper.class, IssueMapper.class})
public interface InteractMapper extends EntityMapper<InteractDTO, Interact> {

    @Mapping(source = "you.id", target = "youId")
    @Mapping(source = "issue.id", target = "issueId")
    @Mapping(source = "parent.id", target = "parentId")
    @Mapping(source = "me.id", target = "meId")
    InteractDTO toDto(Interact interact);

    @Mapping(target = "children", ignore = true)
    @Mapping(target = "removeChild", ignore = true)
    @Mapping(source = "youId", target = "you")
    @Mapping(source = "issueId", target = "issue")
    @Mapping(source = "parentId", target = "parent")
    @Mapping(source = "meId", target = "me")
    Interact toEntity(InteractDTO interactDTO);

    default Interact fromId(Long id) {
        if (id == null) {
            return null;
        }
        Interact interact = new Interact();
        interact.setId(id);
        return interact;
    }
}
