package com.nuri.pangea.service.mapper;

import com.nuri.pangea.domain.Issue;
import com.nuri.pangea.domain.IssueOption;
import com.nuri.pangea.service.dto.IssueOptionDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-11-28T22:45:51+0700",
    comments = "version: 1.3.1.Final, compiler: Eclipse JDT (IDE) 1.3.1200.v20200916-0645, environment: Java 15.0.1 (Oracle Corporation)"
)
@Component
public class IssueOptionMapperImpl implements IssueOptionMapper {

    @Autowired
    private IssueMapper issueMapper;

    @Override
    public List<IssueOption> toEntity(List<IssueOptionDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<IssueOption> list = new ArrayList<IssueOption>( dtoList.size() );
        for ( IssueOptionDTO issueOptionDTO : dtoList ) {
            list.add( toEntity( issueOptionDTO ) );
        }

        return list;
    }

    @Override
    public List<IssueOptionDTO> toDto(List<IssueOption> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<IssueOptionDTO> list = new ArrayList<IssueOptionDTO>( entityList.size() );
        for ( IssueOption issueOption : entityList ) {
            list.add( toDto( issueOption ) );
        }

        return list;
    }

    @Override
    public IssueOptionDTO toDto(IssueOption issueOption) {
        if ( issueOption == null ) {
            return null;
        }

        IssueOptionDTO issueOptionDTO = new IssueOptionDTO();

        issueOptionDTO.setIssueId( issueOptionIssueId( issueOption ) );
        issueOptionDTO.setId( issueOption.getId() );
        issueOptionDTO.setName( issueOption.getName() );
        issueOptionDTO.setDescription( issueOption.getDescription() );
        issueOptionDTO.setCoin( issueOption.getCoin() );
        issueOptionDTO.setPoint( issueOption.getPoint() );
        issueOptionDTO.setStatus( issueOption.getStatus() );
        issueOptionDTO.setCreated( issueOption.getCreated() );
        issueOptionDTO.setModified( issueOption.getModified() );

        return issueOptionDTO;
    }

    @Override
    public IssueOption toEntity(IssueOptionDTO issueOptionDTO) {
        if ( issueOptionDTO == null ) {
            return null;
        }

        IssueOption issueOption = new IssueOption();

        issueOption.setIssue( issueMapper.fromId( issueOptionDTO.getIssueId() ) );
        issueOption.setId( issueOptionDTO.getId() );
        issueOption.setName( issueOptionDTO.getName() );
        issueOption.setDescription( issueOptionDTO.getDescription() );
        issueOption.setCoin( issueOptionDTO.getCoin() );
        issueOption.setPoint( issueOptionDTO.getPoint() );
        issueOption.setStatus( issueOptionDTO.getStatus() );
        issueOption.setCreated( issueOptionDTO.getCreated() );
        issueOption.setModified( issueOptionDTO.getModified() );

        return issueOption;
    }

    private Long issueOptionIssueId(IssueOption issueOption) {
        if ( issueOption == null ) {
            return null;
        }
        Issue issue = issueOption.getIssue();
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
