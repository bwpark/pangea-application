package com.nuri.pangea.service.mapper;

import com.nuri.pangea.domain.Avatar;
import com.nuri.pangea.domain.Category2avatar;
import com.nuri.pangea.domain.User;
import com.nuri.pangea.service.dto.AvatarDTO;
import java.util.ArrayList;
import java.util.Arrays;
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
public class AvatarMapperImpl implements AvatarMapper {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private Category2avatarMapper category2avatarMapper;

    @Override
    public List<Avatar> toEntity(List<AvatarDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Avatar> list = new ArrayList<Avatar>( dtoList.size() );
        for ( AvatarDTO avatarDTO : dtoList ) {
            list.add( toEntity( avatarDTO ) );
        }

        return list;
    }

    @Override
    public List<AvatarDTO> toDto(List<Avatar> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<AvatarDTO> list = new ArrayList<AvatarDTO>( entityList.size() );
        for ( Avatar avatar : entityList ) {
            list.add( toDto( avatar ) );
        }

        return list;
    }

    @Override
    public AvatarDTO toDto(Avatar avatar) {
        if ( avatar == null ) {
            return null;
        }

        AvatarDTO avatarDTO = new AvatarDTO();

        avatarDTO.setUserId( avatarUserId( avatar ) );
        avatarDTO.setCategoryId( avatarCategoryId( avatar ) );
        avatarDTO.setId( avatar.getId() );
        avatarDTO.setCategoryName( avatar.getCategoryName() );
        avatarDTO.setName( avatar.getName() );
        avatarDTO.setDescription( avatar.getDescription() );
        avatarDTO.setText( avatar.getText() );
        byte[] logo = avatar.getLogo();
        if ( logo != null ) {
            avatarDTO.setLogo( Arrays.copyOf( logo, logo.length ) );
        }
        avatarDTO.setLogoContentType( avatar.getLogoContentType() );
        byte[] banner = avatar.getBanner();
        if ( banner != null ) {
            avatarDTO.setBanner( Arrays.copyOf( banner, banner.length ) );
        }
        avatarDTO.setBannerContentType( avatar.getBannerContentType() );
        avatarDTO.setCoin( avatar.getCoin() );
        avatarDTO.setPoint( avatar.getPoint() );
        avatarDTO.setLike( avatar.getLike() );
        avatarDTO.setDislike( avatar.getDislike() );
        avatarDTO.setGrade( avatar.getGrade() );
        avatarDTO.setCredit( avatar.getCredit() );
        avatarDTO.setViews( avatar.getViews() );
        avatarDTO.setComments( avatar.getComments() );
        avatarDTO.setStatus( avatar.getStatus() );
        avatarDTO.setCreated( avatar.getCreated() );
        avatarDTO.setModified( avatar.getModified() );

        return avatarDTO;
    }

    @Override
    public Avatar toEntity(AvatarDTO avatarDTO) {
        if ( avatarDTO == null ) {
            return null;
        }

        Avatar avatar = new Avatar();

        avatar.setCategory( category2avatarMapper.fromId( avatarDTO.getCategoryId() ) );
        avatar.setUser( userMapper.userFromId( avatarDTO.getUserId() ) );
        avatar.setId( avatarDTO.getId() );
        avatar.setCategoryName( avatarDTO.getCategoryName() );
        avatar.setName( avatarDTO.getName() );
        avatar.setDescription( avatarDTO.getDescription() );
        avatar.setText( avatarDTO.getText() );
        byte[] logo = avatarDTO.getLogo();
        if ( logo != null ) {
            avatar.setLogo( Arrays.copyOf( logo, logo.length ) );
        }
        avatar.setLogoContentType( avatarDTO.getLogoContentType() );
        byte[] banner = avatarDTO.getBanner();
        if ( banner != null ) {
            avatar.setBanner( Arrays.copyOf( banner, banner.length ) );
        }
        avatar.setBannerContentType( avatarDTO.getBannerContentType() );
        avatar.setCoin( avatarDTO.getCoin() );
        avatar.setPoint( avatarDTO.getPoint() );
        avatar.setLike( avatarDTO.getLike() );
        avatar.setDislike( avatarDTO.getDislike() );
        avatar.setGrade( avatarDTO.getGrade() );
        avatar.setCredit( avatarDTO.getCredit() );
        avatar.setViews( avatarDTO.getViews() );
        avatar.setComments( avatarDTO.getComments() );
        avatar.setStatus( avatarDTO.getStatus() );
        avatar.setCreated( avatarDTO.getCreated() );
        avatar.setModified( avatarDTO.getModified() );

        return avatar;
    }

    private Long avatarUserId(Avatar avatar) {
        if ( avatar == null ) {
            return null;
        }
        User user = avatar.getUser();
        if ( user == null ) {
            return null;
        }
        Long id = user.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long avatarCategoryId(Avatar avatar) {
        if ( avatar == null ) {
            return null;
        }
        Category2avatar category = avatar.getCategory();
        if ( category == null ) {
            return null;
        }
        Long id = category.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
