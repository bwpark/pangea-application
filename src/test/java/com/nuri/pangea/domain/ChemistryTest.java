package com.nuri.pangea.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.nuri.pangea.web.rest.TestUtil;

public class ChemistryTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Chemistry.class);
        Chemistry chemistry1 = new Chemistry();
        chemistry1.setId(1L);
        Chemistry chemistry2 = new Chemistry();
        chemistry2.setId(chemistry1.getId());
        assertThat(chemistry1).isEqualTo(chemistry2);
        chemistry2.setId(2L);
        assertThat(chemistry1).isNotEqualTo(chemistry2);
        chemistry1.setId(null);
        assertThat(chemistry1).isNotEqualTo(chemistry2);
    }
}
