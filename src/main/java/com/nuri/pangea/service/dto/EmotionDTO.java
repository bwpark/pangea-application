package com.nuri.pangea.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import com.nuri.pangea.domain.enumeration.EmotionStatus;

/**
 * A DTO for the {@link com.nuri.pangea.domain.Emotion} entity.
 */
public class EmotionDTO implements Serializable {
    
    private Long id;

    @NotNull
    private EmotionStatus status;

    @NotNull
    private Instant created;

    @NotNull
    private Instant modified;


    private Long youId;

    private Long issueId;

    private Long meId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EmotionStatus getStatus() {
        return status;
    }

    public void setStatus(EmotionStatus status) {
        this.status = status;
    }

    public Instant getCreated() {
        return created;
    }

    public void setCreated(Instant created) {
        this.created = created;
    }

    public Instant getModified() {
        return modified;
    }

    public void setModified(Instant modified) {
        this.modified = modified;
    }

    public Long getYouId() {
        return youId;
    }

    public void setYouId(Long avatarId) {
        this.youId = avatarId;
    }

    public Long getIssueId() {
        return issueId;
    }

    public void setIssueId(Long issueId) {
        this.issueId = issueId;
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
        if (!(o instanceof EmotionDTO)) {
            return false;
        }

        return id != null && id.equals(((EmotionDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EmotionDTO{" +
            "id=" + getId() +
            ", status='" + getStatus() + "'" +
            ", created='" + getCreated() + "'" +
            ", modified='" + getModified() + "'" +
            ", youId=" + getYouId() +
            ", issueId=" + getIssueId() +
            ", meId=" + getMeId() +
            "}";
    }
}
