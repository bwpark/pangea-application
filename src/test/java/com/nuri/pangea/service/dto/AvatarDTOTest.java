package com.nuri.pangea.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.nuri.pangea.web.rest.TestUtil;

public class AvatarDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AvatarDTO.class);
        AvatarDTO avatarDTO1 = new AvatarDTO();
        avatarDTO1.setId(1L);
        AvatarDTO avatarDTO2 = new AvatarDTO();
        assertThat(avatarDTO1).isNotEqualTo(avatarDTO2);
        avatarDTO2.setId(avatarDTO1.getId());
        assertThat(avatarDTO1).isEqualTo(avatarDTO2);
        avatarDTO2.setId(2L);
        assertThat(avatarDTO1).isNotEqualTo(avatarDTO2);
        avatarDTO1.setId(null);
        assertThat(avatarDTO1).isNotEqualTo(avatarDTO2);
    }
}
