package com.nuri.pangea.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import com.nuri.pangea.domain.enumeration.ReputeStatus;

/**
 * A DTO for the {@link com.nuri.pangea.domain.Repute} entity.
 */
public class ReputeDTO implements Serializable {
    
    private Long id;

    @Size(max = 1024)
    private String description;

    @NotNull
    private Integer grade;

    @NotNull
    private Integer credit;

    @NotNull
    private ReputeStatus status;

    @NotNull
    private Instant created;

    @NotNull
    private Instant modified;


    private Long youId;

    private Long meId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public ReputeStatus getStatus() {
        return status;
    }

    public void setStatus(ReputeStatus status) {
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
        if (!(o instanceof ReputeDTO)) {
            return false;
        }

        return id != null && id.equals(((ReputeDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ReputeDTO{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
            ", grade=" + getGrade() +
            ", credit=" + getCredit() +
            ", status='" + getStatus() + "'" +
            ", created='" + getCreated() + "'" +
            ", modified='" + getModified() + "'" +
            ", youId=" + getYouId() +
            ", meId=" + getMeId() +
            "}";
    }
}
