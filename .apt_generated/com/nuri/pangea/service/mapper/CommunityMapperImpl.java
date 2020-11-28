package com.nuri.pangea.service.mapper;

import com.nuri.pangea.domain.Avatar;
import com.nuri.pangea.domain.Interact;
import com.nuri.pangea.domain.Issue;
import com.nuri.pangea.domain.IssueOption;
import com.nuri.pangea.domain.IssueResource;
import com.nuri.pangea.service.dto.CommunityDTO;
import com.nuri.pangea.service.dto.CommunityLiteDTO;
import com.nuri.pangea.service.dto.InteractDTO;
import com.nuri.pangea.service.dto.IssueDTO;
import com.nuri.pangea.service.dto.IssueOptionDTO;
import com.nuri.pangea.service.dto.IssueResourceDTO;
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
public class CommunityMapperImpl implements CommunityMapper {

    @Autowired
    private AvatarMapper avatarMapper;
    @Autowired
    private IssueOptionMapper issueOptionMapper;
    @Autowired
    private IssueResourceMapper issueResourceMapper;
    @Autowired
    private InteractMapper interactMapper;

    @Override
    public Issue toEntity(CommunityDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Issue issue = new Issue();

        issue.setId( dto.getId() );
        issue.setCategoryName( dto.getCategoryName() );
        issue.setName( dto.getName() );
        issue.setDescription( dto.getDescription() );
        issue.setText( dto.getText() );
        issue.setCoin( dto.getCoin() );
        issue.setPoint( dto.getPoint() );
        issue.setLike( dto.getLike() );
        issue.setDislike( dto.getDislike() );
        issue.setAuthor( dto.getAuthor() );
        issue.setViews( dto.getViews() );
        issue.setStatus( dto.getStatus() );
        issue.setCreated( dto.getCreated() );
        issue.setModified( dto.getModified() );
        issue.setOptions( issueOptionDTOSetToIssueOptionSet( dto.getOptions() ) );
        issue.setInteracts( interactDTOSetToInteractSet( dto.getInteracts() ) );
        issue.setResources( issueResourceDTOSetToIssueResourceSet( dto.getResources() ) );
        issue.setMe( avatarMapper.toEntity( dto.getMe() ) );

        return issue;
    }

    @Override
    public List<Issue> toEntity(List<CommunityDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Issue> list = new ArrayList<Issue>( dtoList.size() );
        for ( CommunityDTO communityDTO : dtoList ) {
            list.add( toEntity( communityDTO ) );
        }

        return list;
    }

    @Override
    public List<CommunityDTO> toDto(List<Issue> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<CommunityDTO> list = new ArrayList<CommunityDTO>( entityList.size() );
        for ( Issue issue : entityList ) {
            list.add( toDto( issue ) );
        }

        return list;
    }

    @Override
    public CommunityDTO toDto(Issue issue) {
        if ( issue == null ) {
            return null;
        }

        CommunityDTO communityDTO = new CommunityDTO();

        communityDTO.setId( issue.getId() );
        communityDTO.setCategoryName( issue.getCategoryName() );
        communityDTO.setName( issue.getName() );
        communityDTO.setDescription( issue.getDescription() );
        communityDTO.setText( issue.getText() );
        communityDTO.setCoin( issue.getCoin() );
        communityDTO.setPoint( issue.getPoint() );
        communityDTO.setLike( issue.getLike() );
        communityDTO.setDislike( issue.getDislike() );
        communityDTO.setAuthor( issue.getAuthor() );
        communityDTO.setViews( issue.getViews() );
        communityDTO.setStatus( issue.getStatus() );
        communityDTO.setCreated( issue.getCreated() );
        communityDTO.setModified( issue.getModified() );
        communityDTO.setOptions( issueOptionSetToIssueOptionDTOSet( issue.getOptions() ) );
        communityDTO.setResources( issueResourceSetToIssueResourceDTOSet( issue.getResources() ) );
        communityDTO.setInteracts( interactSetToInteractDTOSet( issue.getInteracts() ) );
        communityDTO.setMe( avatarMapper.toDto( issue.getMe() ) );

        return communityDTO;
    }

    @Override
    public CommunityLiteDTO toCommunityLiteDTO(Issue issue) {
        if ( issue == null ) {
            return null;
        }

        CommunityLiteDTO communityLiteDTO = new CommunityLiteDTO();

        communityLiteDTO.setMeId( issueMeId( issue ) );
        communityLiteDTO.setId( issue.getId() );
        communityLiteDTO.setCategoryName( issue.getCategoryName() );
        communityLiteDTO.setName( issue.getName() );
        communityLiteDTO.setDescription( issue.getDescription() );
        communityLiteDTO.setText( issue.getText() );
        communityLiteDTO.setCoin( issue.getCoin() );
        communityLiteDTO.setPoint( issue.getPoint() );
        communityLiteDTO.setLike( issue.getLike() );
        communityLiteDTO.setDislike( issue.getDislike() );
        communityLiteDTO.setAuthor( issue.getAuthor() );
        communityLiteDTO.setViews( issue.getViews() );
        communityLiteDTO.setComments( issue.getComments() );
        communityLiteDTO.setStatus( issue.getStatus() );
        communityLiteDTO.setCreated( issue.getCreated() );
        communityLiteDTO.setModified( issue.getModified() );

        return communityLiteDTO;
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

    protected Set<IssueOption> issueOptionDTOSetToIssueOptionSet(Set<IssueOptionDTO> set) {
        if ( set == null ) {
            return null;
        }

        Set<IssueOption> set1 = new HashSet<IssueOption>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( IssueOptionDTO issueOptionDTO : set ) {
            set1.add( issueOptionMapper.toEntity( issueOptionDTO ) );
        }

        return set1;
    }

    protected Set<Interact> interactDTOSetToInteractSet(Set<InteractDTO> set) {
        if ( set == null ) {
            return null;
        }

        Set<Interact> set1 = new HashSet<Interact>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( InteractDTO interactDTO : set ) {
            set1.add( interactMapper.toEntity( interactDTO ) );
        }

        return set1;
    }

    protected Set<IssueResource> issueResourceDTOSetToIssueResourceSet(Set<IssueResourceDTO> set) {
        if ( set == null ) {
            return null;
        }

        Set<IssueResource> set1 = new HashSet<IssueResource>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( IssueResourceDTO issueResourceDTO : set ) {
            set1.add( issueResourceMapper.toEntity( issueResourceDTO ) );
        }

        return set1;
    }

    protected Set<IssueOptionDTO> issueOptionSetToIssueOptionDTOSet(Set<IssueOption> set) {
        if ( set == null ) {
            return null;
        }

        Set<IssueOptionDTO> set1 = new HashSet<IssueOptionDTO>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( IssueOption issueOption : set ) {
            set1.add( issueOptionMapper.toDto( issueOption ) );
        }

        return set1;
    }

    protected Set<IssueResourceDTO> issueResourceSetToIssueResourceDTOSet(Set<IssueResource> set) {
        if ( set == null ) {
            return null;
        }

        Set<IssueResourceDTO> set1 = new HashSet<IssueResourceDTO>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( IssueResource issueResource : set ) {
            set1.add( issueResourceMapper.toDto( issueResource ) );
        }

        return set1;
    }

    protected Set<InteractDTO> interactSetToInteractDTOSet(Set<Interact> set) {
        if ( set == null ) {
            return null;
        }

        Set<InteractDTO> set1 = new HashSet<InteractDTO>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Interact interact : set ) {
            set1.add( interactMapper.toDto( interact ) );
        }

        return set1;
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
