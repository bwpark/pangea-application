package com.nuri.pangea.service.mapper;


import com.nuri.pangea.domain.*;
import com.nuri.pangea.service.dto.AvatarDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Avatar} and its DTO {@link AvatarDTO}.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class, CategoryMapper.class})
public interface AvatarMapper extends EntityMapper<AvatarDTO, Avatar> {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "category.id", target = "categoryId")
    AvatarDTO toDto(Avatar avatar);

    @Mapping(target = "issues", ignore = true)
    @Mapping(target = "removeIssue", ignore = true)
    @Mapping(target = "interacts", ignore = true)
    @Mapping(target = "removeInteract", ignore = true)
    @Mapping(target = "emotions", ignore = true)
    @Mapping(target = "removeEmotion", ignore = true)
    @Mapping(target = "reputes", ignore = true)
    @Mapping(target = "removeRepute", ignore = true)
    @Mapping(target = "regulars", ignore = true)
    @Mapping(target = "removeRegular", ignore = true)
    @Mapping(target = "reports", ignore = true)
    @Mapping(target = "removeReport", ignore = true)
    @Mapping(target = "chemistries", ignore = true)
    @Mapping(target = "removeChemistry", ignore = true)
    @Mapping(target = "buys", ignore = true)
    @Mapping(target = "removeBuy", ignore = true)
    @Mapping(target = "sales", ignore = true)
    @Mapping(target = "removeSale", ignore = true)
    @Mapping(source = "userId", target = "user")
    @Mapping(source = "categoryId", target = "category")
    Avatar toEntity(AvatarDTO avatarDTO);

    default Avatar fromId(Long id) {
        if (id == null) {
            return null;
        }
        Avatar avatar = new Avatar();
        avatar.setId(id);
        return avatar;
    }
}
