package com.nuri.pangea.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class InteractMapperTest {

    private InteractMapper interactMapper;

    @BeforeEach
    public void setUp() {
        interactMapper = new InteractMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(interactMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(interactMapper.fromId(null)).isNull();
    }
}
