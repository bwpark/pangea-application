package com.nuri.pangea.service.mapper;

import com.nuri.pangea.domain.Issue;
import com.nuri.pangea.domain.IssueResource;
import com.nuri.pangea.service.dto.IssueResourceDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-11-28T11:59:16+0700",
    comments = "version: 1.3.1.Final, compiler: Eclipse JDT (IDE) 1.3.1200.v20200916-0645, environment: Java 15.0.1 (Oracle Corporation)"
)
@Component
public class IssueResourceMapperImpl implements IssueResourceMapper {

    @Autowired
    private IssueMapper issueMapper;

    @Override
    public List<IssueResource> toEntity(List<IssueResourceDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<IssueResource> list = new ArrayList<IssueResource>( dtoList.size() );
        for ( IssueResourceDTO issueResourceDTO : dtoList ) {
            list.add( toEntity( issueResourceDTO ) );
        }

        return list;
    }

    @Override
    public List<IssueResourceDTO> toDto(List<IssueResource> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<IssueResourceDTO> list = new ArrayList<IssueResourceDTO>( entityList.size() );
        for ( IssueResource issueResource : entityList ) {
            list.add( toDto( issueResource ) );
        }

        return list;
    }

    @Override
    public IssueResourceDTO toDto(IssueResource issueResource) {
        if ( issueResource == null ) {
            return null;
        }

        IssueResourceDTO issueResourceDTO = new IssueResourceDTO();

        issueResourceDTO.setIssueId( issueResourceIssueId( issueResource ) );
        issueResourceDTO.setId( issueResource.getId() );
        issueResourceDTO.setType( issueResource.getType() );
        issueResourceDTO.setLink( issueResource.getLink() );
        issueResourceDTO.setCreated( issueResource.getCreated() );

        return issueResourceDTO;
    }

    @Override
    public IssueResource toEntity(IssueResourceDTO issueResourceDTO) {
        if ( issueResourceDTO == null ) {
            return null;
        }

        IssueResource issueResource = new IssueResource();

        issueResource.setIssue( issueMapper.fromId( issueResourceDTO.getIssueId() ) );
        issueResource.setId( issueResourceDTO.getId() );
        issueResource.setType( issueResourceDTO.getType() );
        issueResource.setLink( issueResourceDTO.getLink() );
        issueResource.setCreated( issueResourceDTO.getCreated() );

        return issueResource;
    }

    private Long issueResourceIssueId(IssueResource issueResource) {
        if ( issueResource == null ) {
            return null;
        }
        Issue issue = issueResource.getIssue();
        if ( issue == null ) {
            return null;
        }
        Long id = issue.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
