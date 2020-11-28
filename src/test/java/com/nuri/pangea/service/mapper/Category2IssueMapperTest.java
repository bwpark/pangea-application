package com.nuri.pangea.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Category2IssueMapperTest {

    private Category2IssueMapper category2IssueMapper;

    @BeforeEach
    public void setUp() {
        category2IssueMapper = new Category2IssueMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(category2IssueMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(category2IssueMapper.fromId(null)).isNull();
    }
}
