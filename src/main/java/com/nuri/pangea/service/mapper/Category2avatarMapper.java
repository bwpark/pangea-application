package com.nuri.pangea.service.mapper;

import com.nuri.pangea.domain.*;
import com.nuri.pangea.service.dto.Category2avatarDTO;
import com.nuri.pangea.service.dto.Category2avatarLiteDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Category2avatar} and its DTO {@link Category2avatarLiteDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface Category2avatarMapper extends EntityMapper<Category2avatarLiteDTO, Category2avatar> {
    @Mapping(target = "avatars", ignore = true)
    @Mapping(target = "removeAvatar", ignore = true)
    @Mapping(target = "children", ignore = true)
    @Mapping(target = "removeChild", ignore = true)
    @Mapping(target = "parent", ignore = true)
    Category2avatar toEntity(Category2avatarLiteDTO category2avatarDTO);

    Category2avatarDTO toCategory2avatarDTO(Category2avatar category2avatar);

    default Category2avatar fromId(Long id) {
        if (id == null) {
            return null;
        }
        Category2avatar category2avatar = new Category2avatar();
        category2avatar.setId(id);
        return category2avatar;
    }
}
