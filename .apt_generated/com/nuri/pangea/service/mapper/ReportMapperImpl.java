package com.nuri.pangea.service.mapper;

import com.nuri.pangea.domain.Avatar;
import com.nuri.pangea.domain.Report;
import com.nuri.pangea.service.dto.ReportDTO;
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
public class ReportMapperImpl implements ReportMapper {

    @Autowired
    private AvatarMapper avatarMapper;

    @Override
    public List<Report> toEntity(List<ReportDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Report> list = new ArrayList<Report>( dtoList.size() );
        for ( ReportDTO reportDTO : dtoList ) {
            list.add( toEntity( reportDTO ) );
        }

        return list;
    }

    @Override
    public List<ReportDTO> toDto(List<Report> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<ReportDTO> list = new ArrayList<ReportDTO>( entityList.size() );
        for ( Report report : entityList ) {
            list.add( toDto( report ) );
        }

        return list;
    }

    @Override
    public ReportDTO toDto(Report report) {
        if ( report == null ) {
            return null;
        }

        ReportDTO reportDTO = new ReportDTO();

        reportDTO.setMeId( reportMeId( report ) );
        reportDTO.setYouId( reportYouId( report ) );
        reportDTO.setId( report.getId() );
        reportDTO.setDescription( report.getDescription() );
        reportDTO.setName( report.getName() );
        reportDTO.setStatus( report.getStatus() );
        reportDTO.setCreated( report.getCreated() );
        reportDTO.setModified( report.getModified() );

        return reportDTO;
    }

    @Override
    public Report toEntity(ReportDTO reportDTO) {
        if ( reportDTO == null ) {
            return null;
        }

        Report report = new Report();

        report.setMe( avatarMapper.fromId( reportDTO.getMeId() ) );
        report.setYou( avatarMapper.fromId( reportDTO.getYouId() ) );
        report.setId( reportDTO.getId() );
        report.setDescription( reportDTO.getDescription() );
        report.setName( reportDTO.getName() );
        report.setStatus( reportDTO.getStatus() );
        report.setCreated( reportDTO.getCreated() );
        report.setModified( reportDTO.getModified() );

        return report;
    }

    private Long reportMeId(Report report) {
        if ( report == null ) {
            return null;
        }
        Avatar me = report.getMe();
        if ( me == null ) {
            return null;
        }
        Long id = me.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long reportYouId(Report report) {
        if ( report == null ) {
            return null;
        }
        Avatar you = report.getYou();
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
