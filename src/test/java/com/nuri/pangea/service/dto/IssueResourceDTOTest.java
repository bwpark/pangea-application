package com.nuri.pangea.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.nuri.pangea.web.rest.TestUtil;

public class IssueResourceDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(IssueResourceDTO.class);
        IssueResourceDTO issueResourceDTO1 = new IssueResourceDTO();
        issueResourceDTO1.setId(1L);
        IssueResourceDTO issueResourceDTO2 = new IssueResourceDTO();
        assertThat(issueResourceDTO1).isNotEqualTo(issueResourceDTO2);
        issueResourceDTO2.setId(issueResourceDTO1.getId());
        assertThat(issueResourceDTO1).isEqualTo(issueResourceDTO2);
        issueResourceDTO2.setId(2L);
        assertThat(issueResourceDTO1).isNotEqualTo(issueResourceDTO2);
        issueResourceDTO1.setId(null);
        assertThat(issueResourceDTO1).isNotEqualTo(issueResourceDTO2);
    }
}
