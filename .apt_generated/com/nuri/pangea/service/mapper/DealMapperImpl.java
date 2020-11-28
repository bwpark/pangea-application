package com.nuri.pangea.service.mapper;

import com.nuri.pangea.domain.Avatar;
import com.nuri.pangea.domain.Deal;
import com.nuri.pangea.domain.Issue;
import com.nuri.pangea.domain.Pack;
import com.nuri.pangea.service.dto.DealDTO;
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
public class DealMapperImpl implements DealMapper {

    @Autowired
    private IssueMapper issueMapper;
    @Autowired
    private PackMapper packMapper;
    @Autowired
    private AvatarMapper avatarMapper;

    @Override
    public List<Deal> toEntity(List<DealDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Deal> list = new ArrayList<Deal>( dtoList.size() );
        for ( DealDTO dealDTO : dtoList ) {
            list.add( toEntity( dealDTO ) );
        }

        return list;
    }

    @Override
    public List<DealDTO> toDto(List<Deal> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<DealDTO> list = new ArrayList<DealDTO>( entityList.size() );
        for ( Deal deal : entityList ) {
            list.add( toDto( deal ) );
        }

        return list;
    }

    @Override
    public DealDTO toDto(Deal deal) {
        if ( deal == null ) {
            return null;
        }

        DealDTO dealDTO = new DealDTO();

        dealDTO.setPackId( dealPackId( deal ) );
        dealDTO.setToId( dealToId( deal ) );
        dealDTO.setWithId( dealWithId( deal ) );
        dealDTO.setId( deal.getId() );
        dealDTO.setName( deal.getName() );
        dealDTO.setDescription( deal.getDescription() );
        dealDTO.setQuantity( deal.getQuantity() );
        dealDTO.setUnitPrice( deal.getUnitPrice() );
        dealDTO.setCoin( deal.getCoin() );
        dealDTO.setPoint( deal.getPoint() );
        dealDTO.setStatus( deal.getStatus() );
        dealDTO.setCreated( deal.getCreated() );
        dealDTO.setModified( deal.getModified() );

        return dealDTO;
    }

    @Override
    public Deal toEntity(DealDTO dealDTO) {
        if ( dealDTO == null ) {
            return null;
        }

        Deal deal = new Deal();

        deal.setWith( issueMapper.fromId( dealDTO.getWithId() ) );
        deal.setTo( avatarMapper.fromId( dealDTO.getToId() ) );
        deal.setPack( packMapper.fromId( dealDTO.getPackId() ) );
        deal.setId( dealDTO.getId() );
        deal.setName( dealDTO.getName() );
        deal.setDescription( dealDTO.getDescription() );
        deal.setQuantity( dealDTO.getQuantity() );
        deal.setUnitPrice( dealDTO.getUnitPrice() );
        deal.setCoin( dealDTO.getCoin() );
        deal.setPoint( dealDTO.getPoint() );
        deal.setStatus( dealDTO.getStatus() );
        deal.setCreated( dealDTO.getCreated() );
        deal.setModified( dealDTO.getModified() );

        return deal;
    }

    private Long dealPackId(Deal deal) {
        if ( deal == null ) {
            return null;
        }
        Pack pack = deal.getPack();
        if ( pack == null ) {
            return null;
        }
        Long id = pack.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long dealToId(Deal deal) {
        if ( deal == null ) {
            return null;
        }
        Avatar to = deal.getTo();
        if ( to == null ) {
            return null;
        }
        Long id = to.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long dealWithId(Deal deal) {
        if ( deal == null ) {
            return null;
        }
        Issue with = deal.getWith();
        if ( with == null ) {
            return null;
        }
        Long id = with.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
