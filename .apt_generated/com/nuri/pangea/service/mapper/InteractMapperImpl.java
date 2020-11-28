package com.nuri.pangea.service.mapper;

import com.nuri.pangea.domain.Avatar;
import com.nuri.pangea.domain.Interact;
import com.nuri.pangea.domain.Issue;
import com.nuri.pangea.service.dto.InteractDTO;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-11-28T22:45:50+0700",
    comments = "version: 1.3.1.Final, compiler: Eclipse JDT (IDE) 1.3.1200.v20200916-0645, environment: Java 15.0.1 (Oracle Corporation)"
)
@Component
public class InteractMapperImpl implements InteractMapper {

    @Autowired
    private AvatarMapper avatarMapper;
    @Autowired
    private IssueMapper issueMapper;

    @Override
    public List<Interact> toEntity(List<InteractDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Interact> list = new ArrayList<Interact>( dtoList.size() );
        for ( InteractDTO interactDTO : dtoList ) {
            list.add( toEntity( interactDTO ) );
        }

        return list;
    }

    @Override
    public List<InteractDTO> toDto(List<Interact> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<InteractDTO> list = new ArrayList<InteractDTO>( entityList.size() );
        for ( Interact interact : entityList ) {
            list.add( toDto( interact ) );
        }

        return list;
    }

    @Override
    public InteractDTO toDto(Interact interact) {
        if ( interact == null ) {
            return null;
        }

        InteractDTO interactDTO = new InteractDTO();

        interactDTO.setMeId( interactMeId( interact ) );
        interactDTO.setIssueId( interactIssueId( interact ) );
        interactDTO.setParentId( interactParentId( interact ) );
        interactDTO.setYouId( interactYouId( interact ) );
        interactDTO.setId( interact.getId() );
        interactDTO.setText( interact.getText() );
        interactDTO.setCoin( interact.getCoin() );
        interactDTO.setPoint( interact.getPoint() );
        interactDTO.setLike( interact.getLike() );
        interactDTO.setDislike( interact.getDislike() );
        interactDTO.setAuthor( interact.getAuthor() );
        interactDTO.setStatus( interact.getStatus() );
        interactDTO.setCreated( interact.getCreated() );
        interactDTO.setModified( interact.getModified() );
        Set<Interact> set = interact.getChildren();
        if ( set != null ) {
            interactDTO.setChildren( new HashSet<Interact>( set ) );
        }

        return interactDTO;
    }

    @Override
    public Interact toEntity(InteractDTO interactDTO) {
        if ( interactDTO == null ) {
            return null;
        }

        Interact interact = new Interact();

        interact.setParent( fromId( interactDTO.getParentId() ) );
        interact.setIssue( issueMapper.fromId( interactDTO.getIssueId() ) );
        interact.setMe( avatarMapper.fromId( interactDTO.getMeId() ) );
        interact.setYou( avatarMapper.fromId( interactDTO.getYouId() ) );
        interact.setId( interactDTO.getId() );
        interact.setText( interactDTO.getText() );
        interact.setCoin( interactDTO.getCoin() );
        interact.setPoint( interactDTO.getPoint() );
        interact.setLike( interactDTO.getLike() );
        interact.setDislike( interactDTO.getDislike() );
        interact.setAuthor( interactDTO.getAuthor() );
        interact.setStatus( interactDTO.getStatus() );
        interact.setCreated( interactDTO.getCreated() );
        interact.setModified( interactDTO.getModified() );

        return interact;
    }

    private Long interactMeId(Interact interact) {
        if ( interact == null ) {
            return null;
        }
        Avatar me = interact.getMe();
        if ( me == null ) {
            return null;
        }
        Long id = me.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long interactIssueId(Interact interact) {
        if ( interact == null ) {
            return null;
        }
        Issue issue = interact.getIssue();
        if ( issue == null ) {
            return null;
        }
        Long id = issue.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long interactParentId(Interact interact) {
        if ( interact == null ) {
            return null;
        }
        Interact parent = interact.getParent();
        if ( parent == null ) {
            return null;
        }
        Long id = parent.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long interactYouId(Interact interact) {
        if ( interact == null ) {
            return null;
        }
        Avatar you = interact.getYou();
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
