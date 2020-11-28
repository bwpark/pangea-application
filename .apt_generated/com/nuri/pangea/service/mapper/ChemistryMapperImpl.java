package com.nuri.pangea.service.mapper;

import com.nuri.pangea.domain.Avatar;
import com.nuri.pangea.domain.Chemistry;
import com.nuri.pangea.service.dto.ChemistryDTO;
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
public class ChemistryMapperImpl implements ChemistryMapper {

    @Autowired
    private AvatarMapper avatarMapper;

    @Override
    public List<Chemistry> toEntity(List<ChemistryDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Chemistry> list = new ArrayList<Chemistry>( dtoList.size() );
        for ( ChemistryDTO chemistryDTO : dtoList ) {
            list.add( toEntity( chemistryDTO ) );
        }

        return list;
    }

    @Override
    public List<ChemistryDTO> toDto(List<Chemistry> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<ChemistryDTO> list = new ArrayList<ChemistryDTO>( entityList.size() );
        for ( Chemistry chemistry : entityList ) {
            list.add( toDto( chemistry ) );
        }

        return list;
    }

    @Override
    public ChemistryDTO toDto(Chemistry chemistry) {
        if ( chemistry == null ) {
            return null;
        }

        ChemistryDTO chemistryDTO = new ChemistryDTO();

        chemistryDTO.setMeId( chemistryMeId( chemistry ) );
        chemistryDTO.setYouId( chemistryYouId( chemistry ) );
        chemistryDTO.setId( chemistry.getId() );
        chemistryDTO.setYourName( chemistry.getYourName() );
        chemistryDTO.setToYou( chemistry.getToYou() );
        chemistryDTO.setToMe( chemistry.getToMe() );
        chemistryDTO.setCreated( chemistry.getCreated() );

        return chemistryDTO;
    }

    @Override
    public Chemistry toEntity(ChemistryDTO chemistryDTO) {
        if ( chemistryDTO == null ) {
            return null;
        }

        Chemistry chemistry = new Chemistry();

        chemistry.setMe( avatarMapper.fromId( chemistryDTO.getMeId() ) );
        chemistry.setYou( avatarMapper.fromId( chemistryDTO.getYouId() ) );
        chemistry.setId( chemistryDTO.getId() );
        chemistry.setYourName( chemistryDTO.getYourName() );
        chemistry.setToYou( chemistryDTO.getToYou() );
        chemistry.setToMe( chemistryDTO.getToMe() );
        chemistry.setCreated( chemistryDTO.getCreated() );

        return chemistry;
    }

    private Long chemistryMeId(Chemistry chemistry) {
        if ( chemistry == null ) {
            return null;
        }
        Avatar me = chemistry.getMe();
        if ( me == null ) {
            return null;
        }
        Long id = me.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long chemistryYouId(Chemistry chemistry) {
        if ( chemistry == null ) {
            return null;
        }
        Avatar you = chemistry.getYou();
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
