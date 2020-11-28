package com.nuri.pangea.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import com.nuri.pangea.domain.enumeration.DealStatus;

/**
 * A DTO for the {@link com.nuri.pangea.domain.Deal} entity.
 */
public class DealDTO implements Serializable {
    
    private Long id;

    @NotNull
    @Size(max = 128)
    private String name;

    @Size(max = 1024)
    private String description;

    @NotNull
    private Integer quantity;

    @NotNull
    private Integer unitPrice;

    @NotNull
    private Integer coin;

    @NotNull
    private Integer point;

    @NotNull
    private DealStatus status;

    @NotNull
    private Instant created;

    @NotNull
    private Instant modified;


    private Long withId;

    private Long packId;

    private Long toId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Integer unitPrice) {
        this.unitPrice = unitPrice;
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

    public DealStatus getStatus() {
        return status;
    }

    public void setStatus(DealStatus status) {
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

    public Long getWithId() {
        return withId;
    }

    public void setWithId(Long issueId) {
        this.withId = issueId;
    }

    public Long getPackId() {
        return packId;
    }

    public void setPackId(Long packId) {
        this.packId = packId;
    }

    public Long getToId() {
        return toId;
    }

    public void setToId(Long avatarId) {
        this.toId = avatarId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DealDTO)) {
            return false;
        }

        return id != null && id.equals(((DealDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DealDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", quantity=" + getQuantity() +
            ", unitPrice=" + getUnitPrice() +
            ", coin=" + getCoin() +
            ", point=" + getPoint() +
            ", status='" + getStatus() + "'" +
            ", created='" + getCreated() + "'" +
            ", modified='" + getModified() + "'" +
            ", withId=" + getWithId() +
            ", packId=" + getPackId() +
            ", toId=" + getToId() +
            "}";
    }
}
