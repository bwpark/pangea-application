package com.nuri.pangea.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.nuri.pangea.web.rest.TestUtil;

public class Category2avatarDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(Category2avatarDTO.class);
        Category2avatarDTO category2avatarDTO1 = new Category2avatarDTO();
        category2avatarDTO1.setId(1L);
        Category2avatarDTO category2avatarDTO2 = new Category2avatarDTO();
        assertThat(category2avatarDTO1).isNotEqualTo(category2avatarDTO2);
        category2avatarDTO2.setId(category2avatarDTO1.getId());
        assertThat(category2avatarDTO1).isEqualTo(category2avatarDTO2);
        category2avatarDTO2.setId(2L);
        assertThat(category2avatarDTO1).isNotEqualTo(category2avatarDTO2);
        category2avatarDTO1.setId(null);
        assertThat(category2avatarDTO1).isNotEqualTo(category2avatarDTO2);
    }
}
