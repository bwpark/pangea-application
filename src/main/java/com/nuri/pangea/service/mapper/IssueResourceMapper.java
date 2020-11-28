package com.nuri.pangea.service.mapper;


import com.nuri.pangea.domain.*;
import com.nuri.pangea.service.dto.IssueResourceDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link IssueResource} and its DTO {@link IssueResourceDTO}.
 */
@Mapper(componentModel = "spring", uses = {IssueMapper.class})
public interface IssueResourceMapper extends EntityMapper<IssueResourceDTO, IssueResource> {

    @Mapping(source = "issue.id", target = "issueId")
    IssueResourceDTO toDto(IssueResource issueResource);

    @Mapping(source = "issueId", target = "issue")
    IssueResource toEntity(IssueResourceDTO issueResourceDTO);

    default IssueResource fromId(Long id) {
        if (id == null) {
            return null;
        }
        IssueResource issueResource = new IssueResource();
        issueResource.setId(id);
        return issueResource;
    }
}
