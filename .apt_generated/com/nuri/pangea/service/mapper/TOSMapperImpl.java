package com.nuri.pangea.service.mapper;

import com.nuri.pangea.domain.TOS;
import com.nuri.pangea.service.dto.TOSDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-11-28T11:59:16+0700",
    comments = "version: 1.3.1.Final, compiler: Eclipse JDT (IDE) 1.3.1200.v20200916-0645, environment: Java 15.0.1 (Oracle Corporation)"
)
@Component
public class TOSMapperImpl implements TOSMapper {

    @Override
    public TOS toEntity(TOSDTO dto) {
        if ( dto == null ) {
            return null;
        }

        TOS tOS = new TOS();

        tOS.setId( dto.getId() );
        tOS.setPolicy( dto.getPolicy() );
        tOS.setText( dto.getText() );

        return tOS;
    }

    @Override
    public TOSDTO toDto(TOS entity) {
        if ( entity == null ) {
            return null;
        }

        TOSDTO tOSDTO = new TOSDTO();

        tOSDTO.setId( entity.getId() );
        tOSDTO.setPolicy( entity.getPolicy() );
        tOSDTO.setText( entity.getText() );

        return tOSDTO;
    }

    @Override
    public List<TOS> toEntity(List<TOSDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<TOS> list = new ArrayList<TOS>( dtoList.size() );
        for ( TOSDTO tOSDTO : dtoList ) {
            list.add( toEntity( tOSDTO ) );
        }

        return list;
    }

    @Override
    public List<TOSDTO> toDto(List<TOS> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<TOSDTO> list = new ArrayList<TOSDTO>( entityList.size() );
        for ( TOS tOS : entityList ) {
            list.add( toDto( tOS ) );
        }

        return list;
    }
}
