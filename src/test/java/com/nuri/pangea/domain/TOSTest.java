package com.nuri.pangea.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.nuri.pangea.web.rest.TestUtil;

public class TOSTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TOS.class);
        TOS tOS1 = new TOS();
        tOS1.setId(1L);
        TOS tOS2 = new TOS();
        tOS2.setId(tOS1.getId());
        assertThat(tOS1).isEqualTo(tOS2);
        tOS2.setId(2L);
        assertThat(tOS1).isNotEqualTo(tOS2);
        tOS1.setId(null);
        assertThat(tOS1).isNotEqualTo(tOS2);
    }
}
