package com.nuri.pangea.service.mapper;

import com.nuri.pangea.domain.Avatar;
import com.nuri.pangea.domain.Repute;
import com.nuri.pangea.service.dto.ReputeDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-11-28T11:59:17+0700",
    comments = "version: 1.3.1.Final, compiler: Eclipse JDT (IDE) 1.3.1200.v20200916-0645, environment: Java 15.0.1 (Oracle Corporation)"
)
@Component
public class ReputeMapperImpl implements ReputeMapper {

    @Autowired
    private AvatarMapper avatarMapper;

    @Override
    public List<Repute> toEntity(List<ReputeDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Repute> list = new ArrayList<Repute>( dtoList.size() );
        for ( ReputeDTO reputeDTO : dtoList ) {
            list.add( toEntity( reputeDTO ) );
        }

        return list;
    }

    @Override
    public List<ReputeDTO> toDto(List<Repute> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<ReputeDTO> list = new ArrayList<ReputeDTO>( entityList.size() );
        for ( Repute repute : entityList ) {
            list.add( toDto( repute ) );
        }

        return list;
    }

    @Override
    public ReputeDTO toDto(Repute repute) {
        if ( repute == null ) {
            return null;
        }

        ReputeDTO reputeDTO = new ReputeDTO();

        reputeDTO.setMeId( reputeMeId( repute ) );
        reputeDTO.setYouId( reputeYouId( repute ) );
        reputeDTO.setId( repute.getId() );
        reputeDTO.setDescription( repute.getDescription() );
        reputeDTO.setGrade( repute.getGrade() );
        reputeDTO.setCredit( repute.getCredit() );
        reputeDTO.setStatus( repute.getStatus() );
        reputeDTO.setCreated( repute.getCreated() );
        reputeDTO.setModified( repute.getModified() );

        return reputeDTO;
    }

    @Override
    public Repute toEntity(ReputeDTO reputeDTO) {
        if ( reputeDTO == null ) {
            return null;
        }

        Repute repute = new Repute();

        repute.setMe( avatarMapper.fromId( reputeDTO.getMeId() ) );
        repute.setYou( avatarMapper.fromId( reputeDTO.getYouId() ) );
        repute.setId( reputeDTO.getId() );
        repute.setDescription( reputeDTO.getDescription() );
        repute.setGrade( reputeDTO.getGrade() );
        repute.setCredit( reputeDTO.getCredit() );
        repute.setStatus( reputeDTO.getStatus() );
        repute.setCreated( reputeDTO.getCreated() );
        repute.setModified( reputeDTO.getModified() );

        return repute;
    }

    private Long reputeMeId(Repute repute) {
        if ( repute == null ) {
            return null;
        }
        Avatar me = repute.getMe();
        if ( me == null ) {
            return null;
        }
        Long id = me.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long reputeYouId(Repute repute) {
        if ( repute == null ) {
            return null;
        }
        Avatar you = repute.getYou();
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
