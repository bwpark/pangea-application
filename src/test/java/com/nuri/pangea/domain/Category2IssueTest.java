package com.nuri.pangea.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.nuri.pangea.web.rest.TestUtil;

public class Category2IssueTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Category2Issue.class);
        Category2Issue category2Issue1 = new Category2Issue();
        category2Issue1.setId(1L);
        Category2Issue category2Issue2 = new Category2Issue();
        category2Issue2.setId(category2Issue1.getId());
        assertThat(category2Issue1).isEqualTo(category2Issue2);
        category2Issue2.setId(2L);
        assertThat(category2Issue1).isNotEqualTo(category2Issue2);
        category2Issue1.setId(null);
        assertThat(category2Issue1).isNotEqualTo(category2Issue2);
    }
}
