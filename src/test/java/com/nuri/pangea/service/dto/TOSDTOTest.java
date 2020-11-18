package com.nuri.pangea.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.nuri.pangea.web.rest.TestUtil;

public class TOSDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TOSDTO.class);
        TOSDTO tOSDTO1 = new TOSDTO();
        tOSDTO1.setId(1L);
        TOSDTO tOSDTO2 = new TOSDTO();
        assertThat(tOSDTO1).isNotEqualTo(tOSDTO2);
        tOSDTO2.setId(tOSDTO1.getId());
        assertThat(tOSDTO1).isEqualTo(tOSDTO2);
        tOSDTO2.setId(2L);
        assertThat(tOSDTO1).isNotEqualTo(tOSDTO2);
        tOSDTO1.setId(null);
        assertThat(tOSDTO1).isNotEqualTo(tOSDTO2);
    }
}
