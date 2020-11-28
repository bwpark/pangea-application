package com.nuri.pangea.service.mapper;

import com.nuri.pangea.domain.Deal;
import com.nuri.pangea.domain.DealOption;
import com.nuri.pangea.service.dto.DealOptionDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-11-28T22:45:50+0700",
    comments = "version: 1.3.1.Final, compiler: Eclipse JDT (IDE) 1.3.1200.v20200916-0645, environment: Java 15.0.1 (Oracle Corporation)"
)
@Component
public class DealOptionMapperImpl implements DealOptionMapper {

    @Autowired
    private DealMapper dealMapper;

    @Override
    public List<DealOption> toEntity(List<DealOptionDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<DealOption> list = new ArrayList<DealOption>( dtoList.size() );
        for ( DealOptionDTO dealOptionDTO : dtoList ) {
            list.add( toEntity( dealOptionDTO ) );
        }

        return list;
    }

    @Override
    public List<DealOptionDTO> toDto(List<DealOption> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<DealOptionDTO> list = new ArrayList<DealOptionDTO>( entityList.size() );
        for ( DealOption dealOption : entityList ) {
            list.add( toDto( dealOption ) );
        }

        return list;
    }

    @Override
    public DealOptionDTO toDto(DealOption dealOption) {
        if ( dealOption == null ) {
            return null;
        }

        DealOptionDTO dealOptionDTO = new DealOptionDTO();

        dealOptionDTO.setPackId( dealOptionPackId( dealOption ) );
        dealOptionDTO.setId( dealOption.getId() );
        dealOptionDTO.setName( dealOption.getName() );
        dealOptionDTO.setStatus( dealOption.getStatus() );
        dealOptionDTO.setCreated( dealOption.getCreated() );
        dealOptionDTO.setModified( dealOption.getModified() );

        return dealOptionDTO;
    }

    @Override
    public DealOption toEntity(DealOptionDTO dealOptionDTO) {
        if ( dealOptionDTO == null ) {
            return null;
        }

        DealOption dealOption = new DealOption();

        dealOption.setPack( dealMapper.fromId( dealOptionDTO.getPackId() ) );
        dealOption.setId( dealOptionDTO.getId() );
        dealOption.setName( dealOptionDTO.getName() );
        dealOption.setStatus( dealOptionDTO.getStatus() );
        dealOption.setCreated( dealOptionDTO.getCreated() );
        dealOption.setModified( dealOptionDTO.getModified() );

        return dealOption;
    }

    private Long dealOptionPackId(DealOption dealOption) {
        if ( dealOption == null ) {
            return null;
        }
        Deal pack = dealOption.getPack();
        if ( pack == null ) {
            return null;
        }
        Long id = pack.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
