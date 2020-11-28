package com.nuri.pangea.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.nuri.pangea.web.rest.TestUtil;

public class InteractTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Interact.class);
        Interact interact1 = new Interact();
        interact1.setId(1L);
        Interact interact2 = new Interact();
        interact2.setId(interact1.getId());
        assertThat(interact1).isEqualTo(interact2);
        interact2.setId(2L);
        assertThat(interact1).isNotEqualTo(interact2);
        interact1.setId(null);
        assertThat(interact1).isNotEqualTo(interact2);
    }
}
