package com.nuri.pangea.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class EmotionMapperTest {

    private EmotionMapper emotionMapper;

    @BeforeEach
    public void setUp() {
        emotionMapper = new EmotionMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(emotionMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(emotionMapper.fromId(null)).isNull();
    }
}
