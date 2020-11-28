package com.nuri.pangea.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.nuri.pangea.web.rest.TestUtil;

public class InteractDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(InteractDTO.class);
        InteractDTO interactDTO1 = new InteractDTO();
        interactDTO1.setId(1L);
        InteractDTO interactDTO2 = new InteractDTO();
        assertThat(interactDTO1).isNotEqualTo(interactDTO2);
        interactDTO2.setId(interactDTO1.getId());
        assertThat(interactDTO1).isEqualTo(interactDTO2);
        interactDTO2.setId(2L);
        assertThat(interactDTO1).isNotEqualTo(interactDTO2);
        interactDTO1.setId(null);
        assertThat(interactDTO1).isNotEqualTo(interactDTO2);
    }
}
