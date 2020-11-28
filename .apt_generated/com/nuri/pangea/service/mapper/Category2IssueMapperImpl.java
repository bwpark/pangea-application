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
    date = "2020-11-28T22:45:51+0700",
    comments = "version: 1.3.1.Final, compiler: Eclipse JDT (IDE) 1.3.1200.v20200916-0645, environment: Java 15.0.1 (Oracle Corporation)"
)
@Component
public class Category2IssueMapperImpl implements Category2IssueMapper {

    @Override
    public Category2Issue toEntity(Category2IssueLiteDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Category2Issue category2Issue = new Category2Issue();

        category2Issue.setId( dto.getId() );
        category2Issue.setIcon( dto.getIcon() );
        category2Issue.setName( dto.getName() );
        category2Issue.setDescription( dto.getDescription() );
        category2Issue.setStatus( dto.getStatus() );
        category2Issue.setCreated( dto.getCreated() );
        category2Issue.setModified( dto.getModified() );

        return category2Issue;
    }

    @Override
    public Category2IssueLiteDTO toDto(Category2Issue entity) {
        if ( entity == null ) {
            return null;
        }

        Category2IssueLiteDTO category2IssueLiteDTO = new Category2IssueLiteDTO();

        category2IssueLiteDTO.setId( entity.getId() );
        category2IssueLiteDTO.setIcon( entity.getIcon() );
        category2IssueLiteDTO.setName( entity.getName() );
        category2IssueLiteDTO.setDescription( entity.getDescription() );
        category2IssueLiteDTO.setStatus( entity.getStatus() );
        category2IssueLiteDTO.setCreated( entity.getCreated() );
        category2IssueLiteDTO.setModified( entity.getModified() );

        return category2IssueLiteDTO;
    }

    @Override
    public List<Category2Issue> toEntity(List<Category2IssueLiteDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Category2Issue> list = new ArrayList<Category2Issue>( dtoList.size() );
        for ( Category2IssueLiteDTO category2IssueLiteDTO : dtoList ) {
            list.add( toEntity( category2IssueLiteDTO ) );
        }

        return list;
    }

    @Override
    public List<Category2IssueLiteDTO> toDto(List<Category2Issue> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<Category2IssueLiteDTO> list = new ArrayList<Category2IssueLiteDTO>( entityList.size() );
        for ( Category2Issue category2Issue : entityList ) {
            list.add( toDto( category2Issue ) );
        }

        return list;
    }

    @Override
    public Category2IssueDTO toCategory2IssueDTO(Category2Issue category2issue) {
        if ( category2issue == null ) {
            return null;
        }

        Category2IssueDTO category2IssueDTO = new Category2IssueDTO();

        category2IssueDTO.setId( category2issue.getId() );
        category2IssueDTO.setIcon( category2issue.getIcon() );
        category2IssueDTO.setName( category2issue.getName() );
        category2IssueDTO.setDescription( category2issue.getDescription() );
        category2IssueDTO.setStatus( category2issue.getStatus() );
        category2IssueDTO.setCreated( category2issue.getCreated() );
        category2IssueDTO.setModified( category2issue.getModified() );
        category2IssueDTO.setChildren( category2IssueSetToCategory2IssueDTOSet( category2issue.getChildren() ) );

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
