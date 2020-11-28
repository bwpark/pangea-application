package com.nuri.pangea.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.nuri.pangea.web.rest.TestUtil;

public class RegularTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Regular.class);
        Regular regular1 = new Regular();
        regular1.setId(1L);
        Regular regular2 = new Regular();
        regular2.setId(regular1.getId());
        assertThat(regular1).isEqualTo(regular2);
        regular2.setId(2L);
        assertThat(regular1).isNotEqualTo(regular2);
        regular1.setId(null);
        assertThat(regular1).isNotEqualTo(regular2);
    }
}
