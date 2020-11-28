package com.nuri.pangea.service.mapper;

import com.nuri.pangea.domain.Category2avatar;
import com.nuri.pangea.service.dto.Category2avatarDTO;
import com.nuri.pangea.service.dto.Category2avatarLiteDTO;
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
public class Category2avatarMapperImpl implements Category2avatarMapper {

    @Override
    public Category2avatarLiteDTO toDto(Category2avatar entity) {
        if ( entity == null ) {
            return null;
        }

        Category2avatarLiteDTO category2avatarLiteDTO = new Category2avatarLiteDTO();

        category2avatarLiteDTO.setId( entity.getId() );
        category2avatarLiteDTO.setIcon( entity.getIcon() );
        category2avatarLiteDTO.setName( entity.getName() );
        category2avatarLiteDTO.setDescription( entity.getDescription() );
        category2avatarLiteDTO.setStatus( entity.getStatus() );
        category2avatarLiteDTO.setCreated( entity.getCreated() );
        category2avatarLiteDTO.setModified( entity.getModified() );

        return category2avatarLiteDTO;
    }

    @Override
    public List<Category2avatar> toEntity(List<Category2avatarLiteDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Category2avatar> list = new ArrayList<Category2avatar>( dtoList.size() );
        for ( Category2avatarLiteDTO category2avatarLiteDTO : dtoList ) {
            list.add( toEntity( category2avatarLiteDTO ) );
        }

        return list;
    }

    @Override
    public List<Category2avatarLiteDTO> toDto(List<Category2avatar> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<Category2avatarLiteDTO> list = new ArrayList<Category2avatarLiteDTO>( entityList.size() );
        for ( Category2avatar category2avatar : entityList ) {
            list.add( toDto( category2avatar ) );
        }

        return list;
    }

    @Override
    public Category2avatar toEntity(Category2avatarLiteDTO category2avatarDTO) {
        if ( category2avatarDTO == null ) {
            return null;
        }

        Category2avatar category2avatar = new Category2avatar();

        category2avatar.setId( category2avatarDTO.getId() );
        category2avatar.setIcon( category2avatarDTO.getIcon() );
        category2avatar.setName( category2avatarDTO.getName() );
        category2avatar.setDescription( category2avatarDTO.getDescription() );
        category2avatar.setStatus( category2avatarDTO.getStatus() );
        category2avatar.setCreated( category2avatarDTO.getCreated() );
        category2avatar.setModified( category2avatarDTO.getModified() );

        return category2avatar;
    }

    @Override
    public Category2avatarDTO toCategory2avatarDTO(Category2avatar category2avatar) {
        if ( category2avatar == null ) {
            return null;
        }

        Category2avatarDTO category2avatarDTO = new Category2avatarDTO();

        category2avatarDTO.setId( category2avatar.getId() );
        category2avatarDTO.setIcon( category2avatar.getIcon() );
        category2avatarDTO.setName( category2avatar.getName() );
        category2avatarDTO.setDescription( category2avatar.getDescription() );
        category2avatarDTO.setStatus( category2avatar.getStatus() );
        category2avatarDTO.setCreated( category2avatar.getCreated() );
        category2avatarDTO.setModified( category2avatar.getModified() );
        category2avatarDTO.setChildren( category2avatarSetToCategory2avatarDTOSet( category2avatar.getChildren() ) );

        return category2avatarDTO;
    }

    protected Set<Category2avatarDTO> category2avatarSetToCategory2avatarDTOSet(Set<Category2avatar> set) {
        if ( set == null ) {
            return null;
        }

        Set<Category2avatarDTO> set1 = new HashSet<Category2avatarDTO>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Category2avatar category2avatar : set ) {
            set1.add( toCategory2avatarDTO( category2avatar ) );
        }

        return set1;
    }
}
