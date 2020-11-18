package com.nuri.pangea.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import com.nuri.pangea.domain.enumeration.Category2IssueStatus;

/**
 * A DTO for the {@link com.nuri.pangea.domain.Category2Issue} entity.
 */
public class Category2IssueDTO implements Serializable {
    
    private Long id;

    @Size(max = 1024)
    private String icon;

    @NotNull
    @Size(max = 128)
    private String name;

    @Size(max = 1024)
    private String description;

    @NotNull
    private Category2IssueStatus status;

    @NotNull
    private Instant created;

    @NotNull
    private Instant modified;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category2IssueStatus getStatus() {
        return status;
    }

    public void setStatus(Category2IssueStatus status) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Category2IssueDTO)) {
            return false;
        }

        return id != null && id.equals(((Category2IssueDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Category2IssueDTO{" +
            "id=" + getId() +
            ", icon='" + getIcon() + "'" +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", status='" + getStatus() + "'" +
            ", created='" + getCreated() + "'" +
            ", modified='" + getModified() + "'" +
            "}";
    }
}
