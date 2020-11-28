package com.nuri.pangea.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.nuri.pangea.web.rest.TestUtil;

public class DealOptionTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DealOption.class);
        DealOption dealOption1 = new DealOption();
        dealOption1.setId(1L);
        DealOption dealOption2 = new DealOption();
        dealOption2.setId(dealOption1.getId());
        assertThat(dealOption1).isEqualTo(dealOption2);
        dealOption2.setId(2L);
        assertThat(dealOption1).isNotEqualTo(dealOption2);
        dealOption1.setId(null);
        assertThat(dealOption1).isNotEqualTo(dealOption2);
    }
}
