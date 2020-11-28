package com.nuri.pangea.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class RegularMapperTest {

    private RegularMapper regularMapper;

    @BeforeEach
    public void setUp() {
        regularMapper = new RegularMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(regularMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(regularMapper.fromId(null)).isNull();
    }
}
