package com.nuri.pangea.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.nuri.pangea.web.rest.TestUtil;

public class Category2IssueDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(Category2IssueDTO.class);
        Category2IssueDTO category2IssueDTO1 = new Category2IssueDTO();
        category2IssueDTO1.setId(1L);
        Category2IssueDTO category2IssueDTO2 = new Category2IssueDTO();
        assertThat(category2IssueDTO1).isNotEqualTo(category2IssueDTO2);
        category2IssueDTO2.setId(category2IssueDTO1.getId());
        assertThat(category2IssueDTO1).isEqualTo(category2IssueDTO2);
        category2IssueDTO2.setId(2L);
        assertThat(category2IssueDTO1).isNotEqualTo(category2IssueDTO2);
        category2IssueDTO1.setId(null);
        assertThat(category2IssueDTO1).isNotEqualTo(category2IssueDTO2);
    }
}
