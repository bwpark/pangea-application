package com.nuri.pangea.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import com.nuri.pangea.domain.enumeration.ReportStatus;

/**
 * A DTO for the {@link com.nuri.pangea.domain.Report} entity.
 */
public class ReportDTO implements Serializable {
    
    private Long id;

    @Size(max = 1024)
    private String description;

    @NotNull
    @Size(max = 128)
    private String name;

    @NotNull
    private ReportStatus status;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ReportStatus getStatus() {
        return status;
    }

    public void setStatus(ReportStatus status) {
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
        if (!(o instanceof ReportDTO)) {
            return false;
        }

        return id != null && id.equals(((ReportDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ReportDTO{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
            ", name='" + getName() + "'" +
            ", status='" + getStatus() + "'" +
            ", created='" + getCreated() + "'" +
            ", modified='" + getModified() + "'" +
            ", youId=" + getYouId() +
            ", meId=" + getMeId() +
            "}";
    }
}
