package com.nuri.pangea.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ChemistryMapperTest {

    private ChemistryMapper chemistryMapper;

    @BeforeEach
    public void setUp() {
        chemistryMapper = new ChemistryMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(chemistryMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(chemistryMapper.fromId(null)).isNull();
    }
}
