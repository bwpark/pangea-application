package com.nuri.pangea.service.mapper;


import com.nuri.pangea.domain.*;
import com.nuri.pangea.service.dto.EmotionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Emotion} and its DTO {@link EmotionDTO}.
 */
@Mapper(componentModel = "spring", uses = {AvatarMapper.class, IssueMapper.class})
public interface EmotionMapper extends EntityMapper<EmotionDTO, Emotion> {

    @Mapping(source = "you.id", target = "youId")
    @Mapping(source = "issue.id", target = "issueId")
    @Mapping(source = "me.id", target = "meId")
    EmotionDTO toDto(Emotion emotion);

    @Mapping(source = "youId", target = "you")
    @Mapping(source = "issueId", target = "issue")
    @Mapping(source = "meId", target = "me")
    Emotion toEntity(EmotionDTO emotionDTO);

    default Emotion fromId(Long id) {
        if (id == null) {
            return null;
        }
        Emotion emotion = new Emotion();
        emotion.setId(id);
        return emotion;
    }
}
