package com.nuri.pangea.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.nuri.pangea.web.rest.TestUtil;

public class Category2avatarTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Category2avatar.class);
        Category2avatar category2avatar1 = new Category2avatar();
        category2avatar1.setId(1L);
        Category2avatar category2avatar2 = new Category2avatar();
        category2avatar2.setId(category2avatar1.getId());
        assertThat(category2avatar1).isEqualTo(category2avatar2);
        category2avatar2.setId(2L);
        assertThat(category2avatar1).isNotEqualTo(category2avatar2);
        category2avatar1.setId(null);
        assertThat(category2avatar1).isNotEqualTo(category2avatar2);
    }
}
