package com.nuri.pangea.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.nuri.pangea.web.rest.TestUtil;

public class ReputeDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ReputeDTO.class);
        ReputeDTO reputeDTO1 = new ReputeDTO();
        reputeDTO1.setId(1L);
        ReputeDTO reputeDTO2 = new ReputeDTO();
        assertThat(reputeDTO1).isNotEqualTo(reputeDTO2);
        reputeDTO2.setId(reputeDTO1.getId());
        assertThat(reputeDTO1).isEqualTo(reputeDTO2);
        reputeDTO2.setId(2L);
        assertThat(reputeDTO1).isNotEqualTo(reputeDTO2);
        reputeDTO1.setId(null);
        assertThat(reputeDTO1).isNotEqualTo(reputeDTO2);
    }
}
