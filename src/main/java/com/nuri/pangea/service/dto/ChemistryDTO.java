package com.nuri.pangea.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link com.nuri.pangea.domain.Chemistry} entity.
 */
public class ChemistryDTO implements Serializable {
    
    private Long id;

    @NotNull
    @Size(max = 128)
    private String yourName;

    @NotNull
    private Integer toYou;

    @NotNull
    private Integer toMe;

    @NotNull
    private Instant created;


    private Long youId;

    private Long meId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getYourName() {
        return yourName;
    }

    public void setYourName(String yourName) {
        this.yourName = yourName;
    }

    public Integer getToYou() {
        return toYou;
    }

    public void setToYou(Integer toYou) {
        this.toYou = toYou;
    }

    public Integer getToMe() {
        return toMe;
    }

    public void setToMe(Integer toMe) {
        this.toMe = toMe;
    }

    public Instant getCreated() {
        return created;
    }

    public void setCreated(Instant created) {
        this.created = created;
    }

    public Long getYouId() {
        return youId;
    }

    public void setYouId(Long avatarId) {
        this.youId = avatarId;
    }

    public Long getMeId() {
        return meId;
    }

    public void setMeId(Long avatarId) {
        this.meId = avatarId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ChemistryDTO)) {
            return false;
        }

        return id != null && id.equals(((ChemistryDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ChemistryDTO{" +
            "id=" + getId() +
            ", yourName='" + getYourName() + "'" +
            ", toYou=" + getToYou() +
            ", toMe=" + getToMe() +
            ", created='" + getCreated() + "'" +
            ", youId=" + getYouId() +
            ", meId=" + getMeId() +
            "}";
    }
}
