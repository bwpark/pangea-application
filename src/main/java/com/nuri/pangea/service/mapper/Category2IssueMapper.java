package com.nuri.pangea.service.mapper;


import com.nuri.pangea.domain.*;
import com.nuri.pangea.service.dto.Category2IssueDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Category2Issue} and its DTO {@link Category2IssueDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface Category2IssueMapper extends EntityMapper<Category2IssueDTO, Category2Issue> {



    default Category2Issue fromId(Long id) {
        if (id == null) {
            return null;
        }
        Category2Issue category2Issue = new Category2Issue();
        category2Issue.setId(id);
        return category2Issue;
    }
}
