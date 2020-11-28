package com.nuri.pangea.service.mapper;

import com.nuri.pangea.domain.Category2Issue;
import com.nuri.pangea.service.dto.Category2IssueDTO;
import com.nuri.pangea.service.dto.Category2IssueLiteDTO;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-11-28T13:23:58+0700",
    comments = "version: 1.3.1.Final, compiler: Eclipse JDT (IDE) 1.3.1100.v20200828-0941, environment: Java 15 (Oracle Corporation)"
)
@Component
public class Category2IssueMapperImpl implements Category2IssueMapper {

    @Override
    public Category2IssueLiteDTO toDto(Category2Issue arg0) {
        if ( arg0 == null ) {
            return null;
        }

        Category2IssueLiteDTO category2IssueLiteDTO = new Category2IssueLiteDTO();

        category2IssueLiteDTO.setCreated( arg0.getCreated() );
        category2IssueLiteDTO.setDescription( arg0.getDescription() );
        category2IssueLiteDTO.setIcon( arg0.getIcon() );
        category2IssueLiteDTO.setId( arg0.getId() );
        category2IssueLiteDTO.setModified( arg0.getModified() );
        category2IssueLiteDTO.setName( arg0.getName() );
        category2IssueLiteDTO.setStatus( arg0.getStatus() );

        return category2IssueLiteDTO;
    }

    @Override
    public List<Category2IssueLiteDTO> toDto(List<Category2Issue> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<Category2IssueLiteDTO> list = new ArrayList<Category2IssueLiteDTO>( arg0.size() );
        for ( Category2Issue category2Issue : arg0 ) {
            list.add( toDto( category2Issue ) );
        }

        return list;
    }

    @Override
    public Category2Issue toEntity(Category2IssueLiteDTO arg0) {
        if ( arg0 == null ) {
            return null;
        }

        Category2Issue category2Issue = new Category2Issue();

        category2Issue.setCreated( arg0.getCreated() );
        category2Issue.setDescription( arg0.getDescription() );
        category2Issue.setIcon( arg0.getIcon() );
        category2Issue.setModified( arg0.getModified() );
        category2Issue.setName( arg0.getName() );
        category2Issue.setId( arg0.getId() );
        category2Issue.status( arg0.getStatus() );

        return category2Issue;
    }

    @Override
    public List<Category2Issue> toEntity(List<Category2IssueLiteDTO> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<Category2Issue> list = new ArrayList<Category2Issue>( arg0.size() );
        for ( Category2IssueLiteDTO category2IssueLiteDTO : arg0 ) {
            list.add( toEntity( category2IssueLiteDTO ) );
        }

        return list;
    }

    @Override
    public Category2IssueDTO toCategory2IssueDTO(Category2Issue category2issue) {
        if ( category2issue == null ) {
            return null;
        }

        Category2IssueDTO category2IssueDTO = new Category2IssueDTO();

        category2IssueDTO.setChildren( category2IssueSetToCategory2IssueDTOSet( category2issue.getChildren() ) );
        category2IssueDTO.setCreated( category2issue.getCreated() );
        category2IssueDTO.setDescription( category2issue.getDescription() );
        category2IssueDTO.setIcon( category2issue.getIcon() );
        category2IssueDTO.setId( category2issue.getId() );
        category2IssueDTO.setModified( category2issue.getModified() );
        category2IssueDTO.setName( category2issue.getName() );
        category2IssueDTO.setStatus( category2issue.getStatus() );

        return category2IssueDTO;
    }

    protected Set<Category2IssueDTO> category2IssueSetToCategory2IssueDTOSet(Set<Category2Issue> set) {
        if ( set == null ) {
            return null;
        }

        Set<Category2IssueDTO> set1 = new HashSet<Category2IssueDTO>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Category2Issue category2Issue : set ) {
            set1.add( toCategory2IssueDTO( category2Issue ) );
        }

        return set1;
    }
}
