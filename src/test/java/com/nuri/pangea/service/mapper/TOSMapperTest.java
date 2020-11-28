package com.nuri.pangea.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TOSMapperTest {

    private TOSMapper tOSMapper;

    @BeforeEach
    public void setUp() {
        tOSMapper = new TOSMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(tOSMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(tOSMapper.fromId(null)).isNull();
    }
}
