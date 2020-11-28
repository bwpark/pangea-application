package com.nuri.pangea.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import com.nuri.pangea.domain.enumeration.PackStatus;

/**
 * A DTO for the {@link com.nuri.pangea.domain.Pack} entity.
 */
public class PackDTO implements Serializable {
    
    private Long id;

    @Size(max = 1024)
    private String description;

    @NotNull
    private Integer coin;

    @NotNull
    private Integer point;

    @Size(max = 1024)
    private String shipTo;

    @NotNull
    private PackStatus status;

    @NotNull
    private Instant created;

    @NotNull
    private Instant modified;


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

    public Integer getCoin() {
        return coin;
    }

    public void setCoin(Integer coin) {
        this.coin = coin;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public String getShipTo() {
        return shipTo;
    }

    public void setShipTo(String shipTo) {
        this.shipTo = shipTo;
    }

    public PackStatus getStatus() {
        return status;
    }

    public void setStatus(PackStatus status) {
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
        if (!(o instanceof PackDTO)) {
            return false;
        }

        return id != null && id.equals(((PackDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PackDTO{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
            ", coin=" + getCoin() +
            ", point=" + getPoint() +
            ", shipTo='" + getShipTo() + "'" +
            ", status='" + getStatus() + "'" +
            ", created='" + getCreated() + "'" +
            ", modified='" + getModified() + "'" +
            ", meId=" + getMeId() +
            "}";
    }
}
