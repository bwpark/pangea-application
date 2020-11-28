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
    date = "2020-11-28T13:23:13+0700",
    comments = "version: 1.3.1.Final, compiler: Eclipse JDT (IDE) 1.3.1100.v20200828-0941, environment: Java 15 (Oracle Corporation)"
)
@Component
public class Category2avatarMapperImpl implements Category2avatarMapper {

    @Override
    public Category2avatarLiteDTO toDto(Category2avatar arg0) {
        if ( arg0 == null ) {
            return null;
        }

        Category2avatarLiteDTO category2avatarLiteDTO = new Category2avatarLiteDTO();

        category2avatarLiteDTO.setCreated( arg0.getCreated() );
        category2avatarLiteDTO.setDescription( arg0.getDescription() );
        category2avatarLiteDTO.setIcon( arg0.getIcon() );
        category2avatarLiteDTO.setId( arg0.getId() );
        category2avatarLiteDTO.setModified( arg0.getModified() );
        category2avatarLiteDTO.setName( arg0.getName() );
        category2avatarLiteDTO.setStatus( arg0.getStatus() );

        return category2avatarLiteDTO;
    }

    @Override
    public List<Category2avatarLiteDTO> toDto(List<Category2avatar> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<Category2avatarLiteDTO> list = new ArrayList<Category2avatarLiteDTO>( arg0.size() );
        for ( Category2avatar category2avatar : arg0 ) {
            list.add( toDto( category2avatar ) );
        }

        return list;
    }

    @Override
    public List<Category2avatar> toEntity(List<Category2avatarLiteDTO> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<Category2avatar> list = new ArrayList<Category2avatar>( arg0.size() );
        for ( Category2avatarLiteDTO category2avatarLiteDTO : arg0 ) {
            list.add( toEntity( category2avatarLiteDTO ) );
        }

        return list;
    }

    @Override
    public Category2avatar toEntity(Category2avatarLiteDTO category2avatarDTO) {
        if ( category2avatarDTO == null ) {
            return null;
        }

        Category2avatar category2avatar = new Category2avatar();

        category2avatar.setCreated( category2avatarDTO.getCreated() );
        category2avatar.setDescription( category2avatarDTO.getDescription() );
        category2avatar.setIcon( category2avatarDTO.getIcon() );
        category2avatar.setModified( category2avatarDTO.getModified() );
        category2avatar.setName( category2avatarDTO.getName() );
        category2avatar.setId( category2avatarDTO.getId() );
        category2avatar.status( category2avatarDTO.getStatus() );

        return category2avatar;
    }

    @Override
    public Category2avatarDTO toCategory2avatarDTO(Category2avatar category2avatar) {
        if ( category2avatar == null ) {
            return null;
        }

        Category2avatarDTO category2avatarDTO = new Category2avatarDTO();

        category2avatarDTO.setChildren( category2avatarSetToCategory2avatarDTOSet( category2avatar.getChildren() ) );
        category2avatarDTO.setCreated( category2avatar.getCreated() );
        category2avatarDTO.setDescription( category2avatar.getDescription() );
        category2avatarDTO.setIcon( category2avatar.getIcon() );
        category2avatarDTO.setId( category2avatar.getId() );
        category2avatarDTO.setModified( category2avatar.getModified() );
        category2avatarDTO.setName( category2avatar.getName() );
        category2avatarDTO.setStatus( category2avatar.getStatus() );

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
