package com.nuri.pangea.service.mapper;


import com.nuri.pangea.domain.*;
import com.nuri.pangea.service.dto.Category2avatarDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Category2avatar} and its DTO {@link Category2avatarDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface Category2avatarMapper extends EntityMapper<Category2avatarDTO, Category2avatar> {


    @Mapping(target = "avatars", ignore = true)
    @Mapping(target = "removeAvatar", ignore = true)
    Category2avatar toEntity(Category2avatarDTO category2avatarDTO);

    default Category2avatar fromId(Long id) {
        if (id == null) {
            return null;
        }
        Category2avatar category2avatar = new Category2avatar();
        category2avatar.setId(id);
        return category2avatar;
    }
}
