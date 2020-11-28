package com.nuri.pangea.service.dto;

import com.nuri.pangea.domain.enumeration.Category2avatarStatus;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * A DTO for the {@link com.nuri.pangea.domain.Category2avatar} entity.
 */
public class Category2avatarDTO implements Serializable {
    private Long id;

    @Size(max = 1024)
    private String icon;

    @NotNull
    @Size(max = 128)
    private String name;

    @Size(max = 1024)
    private String description;

    private Category2avatarStatus status;

    private Set<Category2avatarDTO> children = new HashSet<>();

    private Instant created;

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

    public Category2avatarStatus getStatus() {
        return status;
    }

    public void setStatus(Category2avatarStatus status) {
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
        if (!(o instanceof Category2avatarDTO)) {
            return false;
        }

        return id != null && id.equals(((Category2avatarDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Category2avatarDTO{" +
            "id=" + getId() +
            ", icon='" + getIcon() + "'" +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", status='" + getStatus() + "'" +
            ", created='" + getCreated() + "'" +
            ", modified='" + getModified() + "'" +
            "}";
    }

    public Set<Category2avatarDTO> getChildren() {
        return children;
    }

    public void setChildren(Set<Category2avatarDTO> children) {
        this.children = children;
    }
}
