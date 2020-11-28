package com.nuri.pangea.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.nuri.pangea.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class Category2IssueLiteDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(Category2IssueLiteDTO.class);
        Category2IssueLiteDTO category2IssueDTO1 = new Category2IssueLiteDTO();
        category2IssueDTO1.setId(1L);
        Category2IssueLiteDTO category2IssueDTO2 = new Category2IssueLiteDTO();
        assertThat(category2IssueDTO1).isNotEqualTo(category2IssueDTO2);
        category2IssueDTO2.setId(category2IssueDTO1.getId());
        assertThat(category2IssueDTO1).isEqualTo(category2IssueDTO2);
        category2IssueDTO2.setId(2L);
        assertThat(category2IssueDTO1).isNotEqualTo(category2IssueDTO2);
        category2IssueDTO1.setId(null);
        assertThat(category2IssueDTO1).isNotEqualTo(category2IssueDTO2);
    }
}
