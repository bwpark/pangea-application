package com.nuri.pangea.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.nuri.pangea.web.rest.TestUtil;

public class RegularDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RegularDTO.class);
        RegularDTO regularDTO1 = new RegularDTO();
        regularDTO1.setId(1L);
        RegularDTO regularDTO2 = new RegularDTO();
        assertThat(regularDTO1).isNotEqualTo(regularDTO2);
        regularDTO2.setId(regularDTO1.getId());
        assertThat(regularDTO1).isEqualTo(regularDTO2);
        regularDTO2.setId(2L);
        assertThat(regularDTO1).isNotEqualTo(regularDTO2);
        regularDTO1.setId(null);
        assertThat(regularDTO1).isNotEqualTo(regularDTO2);
    }
}
