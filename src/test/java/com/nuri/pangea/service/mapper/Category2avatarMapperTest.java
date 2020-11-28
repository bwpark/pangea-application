package com.nuri.pangea.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Category2avatarMapperTest {

    private Category2avatarMapper category2avatarMapper;

    @BeforeEach
    public void setUp() {
        category2avatarMapper = new Category2avatarMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(category2avatarMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(category2avatarMapper.fromId(null)).isNull();
    }
}
