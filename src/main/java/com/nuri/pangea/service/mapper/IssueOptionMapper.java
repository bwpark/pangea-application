package com.nuri.pangea.service.mapper;


import com.nuri.pangea.domain.*;
import com.nuri.pangea.service.dto.IssueOptionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link IssueOption} and its DTO {@link IssueOptionDTO}.
 */
@Mapper(componentModel = "spring", uses = {IssueMapper.class})
public interface IssueOptionMapper extends EntityMapper<IssueOptionDTO, IssueOption> {

    @Mapping(source = "issue.id", target = "issueId")
    IssueOptionDTO toDto(IssueOption issueOption);

    @Mapping(source = "issueId", target = "issue")
    IssueOption toEntity(IssueOptionDTO issueOptionDTO);

    default IssueOption fromId(Long id) {
        if (id == null) {
            return null;
        }
        IssueOption issueOption = new IssueOption();
        issueOption.setId(id);
        return issueOption;
    }
}
