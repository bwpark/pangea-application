package com.nuri.pangea.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.nuri.pangea.web.rest.TestUtil;

public class ChemistryDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ChemistryDTO.class);
        ChemistryDTO chemistryDTO1 = new ChemistryDTO();
        chemistryDTO1.setId(1L);
        ChemistryDTO chemistryDTO2 = new ChemistryDTO();
        assertThat(chemistryDTO1).isNotEqualTo(chemistryDTO2);
        chemistryDTO2.setId(chemistryDTO1.getId());
        assertThat(chemistryDTO1).isEqualTo(chemistryDTO2);
        chemistryDTO2.setId(2L);
        assertThat(chemistryDTO1).isNotEqualTo(chemistryDTO2);
        chemistryDTO1.setId(null);
        assertThat(chemistryDTO1).isNotEqualTo(chemistryDTO2);
    }
}
