package com.nuri.pangea.service.mapper;


import com.nuri.pangea.domain.*;
import com.nuri.pangea.service.dto.Category2IssueDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Category2Issue} and its DTO {@link Category2IssueDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface Category2IssueMapper extends EntityMapper<Category2IssueDTO, Category2Issue> {

    @Mapping(source = "parent.id", target = "parentId")
    Category2IssueDTO toDto(Category2Issue category2Issue);

    @Mapping(target = "children", ignore = true)
    @Mapping(target = "removeChild", ignore = true)
    @Mapping(source = "parentId", target = "parent")
    Category2Issue toEntity(Category2IssueDTO category2IssueDTO);

    default Category2Issue fromId(Long id) {
        if (id == null) {
            return null;
        }
        Category2Issue category2Issue = new Category2Issue();
        category2Issue.setId(id);
        return category2Issue;
    }
}
