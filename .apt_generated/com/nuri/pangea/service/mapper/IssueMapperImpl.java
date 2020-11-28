package com.nuri.pangea.service.mapper;

import com.nuri.pangea.domain.Avatar;
import com.nuri.pangea.domain.Issue;
import com.nuri.pangea.service.dto.IssueDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-11-28T22:45:50+0700",
    comments = "version: 1.3.1.Final, compiler: Eclipse JDT (IDE) 1.3.1200.v20200916-0645, environment: Java 15.0.1 (Oracle Corporation)"
)
@Component
public class IssueMapperImpl implements IssueMapper {

    @Autowired
    private AvatarMapper avatarMapper;

    @Override
    public List<Issue> toEntity(List<IssueDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Issue> list = new ArrayList<Issue>( dtoList.size() );
        for ( IssueDTO issueDTO : dtoList ) {
            list.add( toEntity( issueDTO ) );
        }

        return list;
    }

    @Override
    public List<IssueDTO> toDto(List<Issue> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<IssueDTO> list = new ArrayList<IssueDTO>( entityList.size() );
        for ( Issue issue : entityList ) {
            list.add( toDto( issue ) );
        }

        return list;
    }

    @Override
    public IssueDTO toDto(Issue issue) {
        if ( issue == null ) {
            return null;
        }

        IssueDTO issueDTO = new IssueDTO();

        issueDTO.setMeId( issueMeId( issue ) );
        issueDTO.setId( issue.getId() );
        issueDTO.setCategoryName( issue.getCategoryName() );
        issueDTO.setName( issue.getName() );
        issueDTO.setDescription( issue.getDescription() );
        issueDTO.setText( issue.getText() );
        issueDTO.setCoin( issue.getCoin() );
        issueDTO.setPoint( issue.getPoint() );
        issueDTO.setLike( issue.getLike() );
        issueDTO.setDislike( issue.getDislike() );
        issueDTO.setAuthor( issue.getAuthor() );
        issueDTO.setViews( issue.getViews() );
        issueDTO.setComments( issue.getComments() );
        issueDTO.setStatus( issue.getStatus() );
        issueDTO.setCreated( issue.getCreated() );
        issueDTO.setModified( issue.getModified() );

        return issueDTO;
    }

    @Override
    public Issue toEntity(IssueDTO issueDTO) {
        if ( issueDTO == null ) {
            return null;
        }

        Issue issue = new Issue();

        issue.setMe( avatarMapper.fromId( issueDTO.getMeId() ) );
        issue.setId( issueDTO.getId() );
        issue.setCategoryName( issueDTO.getCategoryName() );
        issue.setName( issueDTO.getName() );
        issue.setDescription( issueDTO.getDescription() );
        issue.setText( issueDTO.getText() );
        issue.setCoin( issueDTO.getCoin() );
        issue.setPoint( issueDTO.getPoint() );
        issue.setLike( issueDTO.getLike() );
        issue.setDislike( issueDTO.getDislike() );
        issue.setAuthor( issueDTO.getAuthor() );
        issue.setViews( issueDTO.getViews() );
        issue.setComments( issueDTO.getComments() );
        issue.setStatus( issueDTO.getStatus() );
        issue.setCreated( issueDTO.getCreated() );
        issue.setModified( issueDTO.getModified() );

        return issue;
    }

    private Long issueMeId(Issue issue) {
        if ( issue == null ) {
            return null;
        }
        Avatar me = issue.getMe();
        if ( me == null ) {
            return null;
        }
        Long id = me.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
