package com.nuri.pangea.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.nuri.pangea.web.rest.TestUtil;

public class ReputeTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Repute.class);
        Repute repute1 = new Repute();
        repute1.setId(1L);
        Repute repute2 = new Repute();
        repute2.setId(repute1.getId());
        assertThat(repute1).isEqualTo(repute2);
        repute2.setId(2L);
        assertThat(repute1).isNotEqualTo(repute2);
        repute1.setId(null);
        assertThat(repute1).isNotEqualTo(repute2);
    }
}
