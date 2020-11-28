package com.nuri.pangea.service.mapper;

import com.nuri.pangea.domain.Avatar;
import com.nuri.pangea.domain.Regular;
import com.nuri.pangea.service.dto.RegularDTO;
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
public class RegularMapperImpl implements RegularMapper {

    @Autowired
    private AvatarMapper avatarMapper;

    @Override
    public List<Regular> toEntity(List<RegularDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Regular> list = new ArrayList<Regular>( dtoList.size() );
        for ( RegularDTO regularDTO : dtoList ) {
            list.add( toEntity( regularDTO ) );
        }

        return list;
    }

    @Override
    public List<RegularDTO> toDto(List<Regular> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<RegularDTO> list = new ArrayList<RegularDTO>( entityList.size() );
        for ( Regular regular : entityList ) {
            list.add( toDto( regular ) );
        }

        return list;
    }

    @Override
    public RegularDTO toDto(Regular regular) {
        if ( regular == null ) {
            return null;
        }

        RegularDTO regularDTO = new RegularDTO();

        regularDTO.setMeId( regularMeId( regular ) );
        regularDTO.setYouId( regularYouId( regular ) );
        regularDTO.setId( regular.getId() );
        regularDTO.setName( regular.getName() );
        regularDTO.setStatus( regular.getStatus() );
        regularDTO.setCreated( regular.getCreated() );
        regularDTO.setModified( regular.getModified() );

        return regularDTO;
    }

    @Override
    public Regular toEntity(RegularDTO regularDTO) {
        if ( regularDTO == null ) {
            return null;
        }

        Regular regular = new Regular();

        regular.setMe( avatarMapper.fromId( regularDTO.getMeId() ) );
        regular.setYou( avatarMapper.fromId( regularDTO.getYouId() ) );
        regular.setId( regularDTO.getId() );
        regular.setName( regularDTO.getName() );
        regular.setStatus( regularDTO.getStatus() );
        regular.setCreated( regularDTO.getCreated() );
        regular.setModified( regularDTO.getModified() );

        return regular;
    }

    private Long regularMeId(Regular regular) {
        if ( regular == null ) {
            return null;
        }
        Avatar me = regular.getMe();
        if ( me == null ) {
            return null;
        }
        Long id = me.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long regularYouId(Regular regular) {
        if ( regular == null ) {
            return null;
        }
        Avatar you = regular.getYou();
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
