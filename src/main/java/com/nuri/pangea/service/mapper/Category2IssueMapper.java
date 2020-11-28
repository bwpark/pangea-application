package com.nuri.pangea.service.mapper;

import com.nuri.pangea.domain.*;
import com.nuri.pangea.service.dto.Category2IssueDTO;
import com.nuri.pangea.service.dto.Category2IssueLiteDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Category2Issue} and its DTO {@link Category2IssueLiteDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface Category2IssueMapper extends EntityMapper<Category2IssueLiteDTO, Category2Issue> {
    Category2IssueDTO toCategory2IssueDTO(Category2Issue category2issue);

    default Category2Issue fromId(Long id) {
        if (id == null) {
            return null;
        }
        Category2Issue category2Issue = new Category2Issue();
        category2Issue.setId(id);
        return category2Issue;
    }
}
