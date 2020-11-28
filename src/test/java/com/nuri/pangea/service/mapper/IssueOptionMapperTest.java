package com.nuri.pangea.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class IssueOptionMapperTest {

    private IssueOptionMapper issueOptionMapper;

    @BeforeEach
    public void setUp() {
        issueOptionMapper = new IssueOptionMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(issueOptionMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(issueOptionMapper.fromId(null)).isNull();
    }
}
