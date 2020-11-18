package com.nuri.pangea.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.nuri.pangea.web.rest.TestUtil;

public class IssueOptionTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(IssueOption.class);
        IssueOption issueOption1 = new IssueOption();
        issueOption1.setId(1L);
        IssueOption issueOption2 = new IssueOption();
        issueOption2.setId(issueOption1.getId());
        assertThat(issueOption1).isEqualTo(issueOption2);
        issueOption2.setId(2L);
        assertThat(issueOption1).isNotEqualTo(issueOption2);
        issueOption1.setId(null);
        assertThat(issueOption1).isNotEqualTo(issueOption2);
    }
}
