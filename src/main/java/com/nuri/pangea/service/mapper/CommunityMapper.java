package com.nuri.pangea.service.mapper;

import com.nuri.pangea.domain.*;
import com.nuri.pangea.service.dto.CommunityDTO;
import com.nuri.pangea.service.dto.CommunityLiteDTO;
import com.nuri.pangea.service.dto.IssueDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Issue} and its DTO {@link IssueDTO}.
 */
@Mapper(
    componentModel = "spring",
    uses = { AvatarMapper.class, IssueOptionMapper.class, IssueResourceMapper.class, InteractMapper.class, EmotionMapper.class }
)
public interface CommunityMapper extends EntityMapper<CommunityDTO, Issue> {
    CommunityDTO toDto(Issue issue);

    /*
	CommunityDTO toCommunityDTO(Issue issue);
	
	 */
    @Mapping(source = "me.id", target = "meId")
    CommunityLiteDTO toCommunityLiteDTO(Issue issue);

    @Mapping(target = "options", ignore = true)
    @Mapping(target = "removeOption", ignore = true)
    @Mapping(target = "resources", ignore = true)
    @Mapping(target = "removeResource", ignore = true)
    @Mapping(target = "interacts", ignore = true)
    @Mapping(target = "removeInteract", ignore = true)
    @Mapping(target = "emotions", ignore = true)
    @Mapping(target = "removeEmotion", ignore = true)
    @Mapping(source = "meId", target = "me")
    Issue toEntity(IssueDTO issueDTO);

    default Issue fromId(Long id) {
        if (id == null) {
            return null;
        }
        Issue issue = new Issue();
        issue.setId(id);
        return issue;
    }
}
