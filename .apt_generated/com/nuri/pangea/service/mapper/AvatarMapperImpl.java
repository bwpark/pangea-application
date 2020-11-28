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
    date = "2020-11-28T13:23:13+0700",
    comments = "version: 1.3.1.Final, compiler: Eclipse JDT (IDE) 1.3.1100.v20200828-0941, environment: Java 15 (Oracle Corporation)"
)
@Component
public class AvatarMapperImpl implements AvatarMapper {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private Category2avatarMapper category2avatarMapper;

    @Override
    public List<AvatarDTO> toDto(List<Avatar> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<AvatarDTO> list = new ArrayList<AvatarDTO>( arg0.size() );
        for ( Avatar avatar : arg0 ) {
            list.add( toDto( avatar ) );
        }

        return list;
    }

    @Override
    public List<Avatar> toEntity(List<AvatarDTO> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<Avatar> list = new ArrayList<Avatar>( arg0.size() );
        for ( AvatarDTO avatarDTO : arg0 ) {
            list.add( toEntity( avatarDTO ) );
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
        byte[] banner = avatar.getBanner();
        if ( banner != null ) {
            avatarDTO.setBanner( Arrays.copyOf( banner, banner.length ) );
        }
        avatarDTO.setBannerContentType( avatar.getBannerContentType() );
        avatarDTO.setCategoryName( avatar.getCategoryName() );
        avatarDTO.setCoin( avatar.getCoin() );
        avatarDTO.setComments( avatar.getComments() );
        avatarDTO.setCreated( avatar.getCreated() );
        avatarDTO.setCredit( avatar.getCredit() );
        avatarDTO.setDescription( avatar.getDescription() );
        avatarDTO.setDislike( avatar.getDislike() );
        avatarDTO.setGrade( avatar.getGrade() );
        avatarDTO.setId( avatar.getId() );
        avatarDTO.setLike( avatar.getLike() );
        byte[] logo = avatar.getLogo();
        if ( logo != null ) {
            avatarDTO.setLogo( Arrays.copyOf( logo, logo.length ) );
        }
        avatarDTO.setLogoContentType( avatar.getLogoContentType() );
        avatarDTO.setModified( avatar.getModified() );
        avatarDTO.setName( avatar.getName() );
        avatarDTO.setPoint( avatar.getPoint() );
        avatarDTO.setStatus( avatar.getStatus() );
        avatarDTO.setText( avatar.getText() );
        avatarDTO.setViews( avatar.getViews() );

        return avatarDTO;
    }

    @Override
    public Avatar toEntity(AvatarDTO avatarDTO) {
        if ( avatarDTO == null ) {
            return null;
        }

        Avatar avatar = new Avatar();

        avatar.setCategory( category2avatarMapper.fromId( avatarDTO.getCategoryId() ) );
        avatar.user( userMapper.userFromId( avatarDTO.getUserId() ) );
        byte[] banner = avatarDTO.getBanner();
        if ( banner != null ) {
            avatar.setBanner( Arrays.copyOf( banner, banner.length ) );
        }
        avatar.setBannerContentType( avatarDTO.getBannerContentType() );
        avatar.setCategoryName( avatarDTO.getCategoryName() );
        avatar.setCoin( avatarDTO.getCoin() );
        avatar.setComments( avatarDTO.getComments() );
        avatar.setCreated( avatarDTO.getCreated() );
        avatar.setCredit( avatarDTO.getCredit() );
        avatar.setDescription( avatarDTO.getDescription() );
        avatar.setDislike( avatarDTO.getDislike() );
        avatar.setGrade( avatarDTO.getGrade() );
        avatar.setLike( avatarDTO.getLike() );
        byte[] logo = avatarDTO.getLogo();
        if ( logo != null ) {
            avatar.setLogo( Arrays.copyOf( logo, logo.length ) );
        }
        avatar.setLogoContentType( avatarDTO.getLogoContentType() );
        avatar.setModified( avatarDTO.getModified() );
        avatar.setName( avatarDTO.getName() );
        avatar.setPoint( avatarDTO.getPoint() );
        avatar.setId( avatarDTO.getId() );
        avatar.status( avatarDTO.getStatus() );
        avatar.text( avatarDTO.getText() );
        avatar.views( avatarDTO.getViews() );

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
