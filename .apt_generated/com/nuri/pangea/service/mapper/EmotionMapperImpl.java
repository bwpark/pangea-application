package com.nuri.pangea.service.mapper;

import com.nuri.pangea.domain.Avatar;
import com.nuri.pangea.domain.Emotion;
import com.nuri.pangea.domain.Issue;
import com.nuri.pangea.service.dto.EmotionDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-11-28T11:59:17+0700",
    comments = "version: 1.3.1.Final, compiler: Eclipse JDT (IDE) 1.3.1200.v20200916-0645, environment: Java 15.0.1 (Oracle Corporation)"
)
@Component
public class EmotionMapperImpl implements EmotionMapper {

    @Autowired
    private AvatarMapper avatarMapper;
    @Autowired
    private IssueMapper issueMapper;

    @Override
    public List<Emotion> toEntity(List<EmotionDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Emotion> list = new ArrayList<Emotion>( dtoList.size() );
        for ( EmotionDTO emotionDTO : dtoList ) {
            list.add( toEntity( emotionDTO ) );
        }

        return list;
    }

    @Override
    public List<EmotionDTO> toDto(List<Emotion> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<EmotionDTO> list = new ArrayList<EmotionDTO>( entityList.size() );
        for ( Emotion emotion : entityList ) {
            list.add( toDto( emotion ) );
        }

        return list;
    }

    @Override
    public EmotionDTO toDto(Emotion emotion) {
        if ( emotion == null ) {
            return null;
        }

        EmotionDTO emotionDTO = new EmotionDTO();

        emotionDTO.setMeId( emotionMeId( emotion ) );
        emotionDTO.setIssueId( emotionIssueId( emotion ) );
        emotionDTO.setYouId( emotionYouId( emotion ) );
        emotionDTO.setId( emotion.getId() );
        emotionDTO.setStatus( emotion.getStatus() );
        emotionDTO.setCreated( emotion.getCreated() );
        emotionDTO.setModified( emotion.getModified() );

        return emotionDTO;
    }

    @Override
    public Emotion toEntity(EmotionDTO emotionDTO) {
        if ( emotionDTO == null ) {
            return null;
        }

        Emotion emotion = new Emotion();

        emotion.setMe( avatarMapper.fromId( emotionDTO.getMeId() ) );
        emotion.setIssue( issueMapper.fromId( emotionDTO.getIssueId() ) );
        emotion.setYou( avatarMapper.fromId( emotionDTO.getYouId() ) );
        emotion.setId( emotionDTO.getId() );
        emotion.setStatus( emotionDTO.getStatus() );
        emotion.setCreated( emotionDTO.getCreated() );
        emotion.setModified( emotionDTO.getModified() );

        return emotion;
    }

    private Long emotionMeId(Emotion emotion) {
        if ( emotion == null ) {
            return null;
        }
        Avatar me = emotion.getMe();
        if ( me == null ) {
            return null;
        }
        Long id = me.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long emotionIssueId(Emotion emotion) {
        if ( emotion == null ) {
            return null;
        }
        Issue issue = emotion.getIssue();
        if ( issue == null ) {
            return null;
        }
        Long id = issue.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long emotionYouId(Emotion emotion) {
        if ( emotion == null ) {
            return null;
        }
        Avatar you = emotion.getYou();
        if ( you == null ) {
            return null;
        }
        Long id = you.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
