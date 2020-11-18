package com.nuri.pangea.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.nuri.pangea.web.rest.TestUtil;

public class EmotionDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EmotionDTO.class);
        EmotionDTO emotionDTO1 = new EmotionDTO();
        emotionDTO1.setId(1L);
        EmotionDTO emotionDTO2 = new EmotionDTO();
        assertThat(emotionDTO1).isNotEqualTo(emotionDTO2);
        emotionDTO2.setId(emotionDTO1.getId());
        assertThat(emotionDTO1).isEqualTo(emotionDTO2);
        emotionDTO2.setId(2L);
        assertThat(emotionDTO1).isNotEqualTo(emotionDTO2);
        emotionDTO1.setId(null);
        assertThat(emotionDTO1).isNotEqualTo(emotionDTO2);
    }
}
