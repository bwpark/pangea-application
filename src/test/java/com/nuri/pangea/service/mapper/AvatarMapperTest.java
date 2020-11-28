package com.nuri.pangea.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class AvatarMapperTest {

    private AvatarMapper avatarMapper;

    @BeforeEach
    public void setUp() {
        avatarMapper = new AvatarMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(avatarMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(avatarMapper.fromId(null)).isNull();
    }
}
