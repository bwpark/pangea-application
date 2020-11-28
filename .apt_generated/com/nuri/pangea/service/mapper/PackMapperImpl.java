package com.nuri.pangea.service.mapper;

import com.nuri.pangea.domain.Avatar;
import com.nuri.pangea.domain.Pack;
import com.nuri.pangea.service.dto.PackDTO;
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
public class PackMapperImpl implements PackMapper {

    @Autowired
    private AvatarMapper avatarMapper;

    @Override
    public List<Pack> toEntity(List<PackDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Pack> list = new ArrayList<Pack>( dtoList.size() );
        for ( PackDTO packDTO : dtoList ) {
            list.add( toEntity( packDTO ) );
        }

        return list;
    }

    @Override
    public List<PackDTO> toDto(List<Pack> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<PackDTO> list = new ArrayList<PackDTO>( entityList.size() );
        for ( Pack pack : entityList ) {
            list.add( toDto( pack ) );
        }

        return list;
    }

    @Override
    public PackDTO toDto(Pack pack) {
        if ( pack == null ) {
            return null;
        }

        PackDTO packDTO = new PackDTO();

        packDTO.setMeId( packMeId( pack ) );
        packDTO.setId( pack.getId() );
        packDTO.setDescription( pack.getDescription() );
        packDTO.setCoin( pack.getCoin() );
        packDTO.setPoint( pack.getPoint() );
        packDTO.setShipTo( pack.getShipTo() );
        packDTO.setStatus( pack.getStatus() );
        packDTO.setCreated( pack.getCreated() );
        packDTO.setModified( pack.getModified() );

        return packDTO;
    }

    @Override
    public Pack toEntity(PackDTO packDTO) {
        if ( packDTO == null ) {
            return null;
        }

        Pack pack = new Pack();

        pack.setMe( avatarMapper.fromId( packDTO.getMeId() ) );
        pack.setId( packDTO.getId() );
        pack.setDescription( packDTO.getDescription() );
        pack.setCoin( packDTO.getCoin() );
        pack.setPoint( packDTO.getPoint() );
        pack.setShipTo( packDTO.getShipTo() );
        pack.setStatus( packDTO.getStatus() );
        pack.setCreated( packDTO.getCreated() );
        pack.setModified( packDTO.getModified() );

        return pack;
    }

    private Long packMeId(Pack pack) {
        if ( pack == null ) {
            return null;
        }
        Avatar me = pack.getMe();
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
