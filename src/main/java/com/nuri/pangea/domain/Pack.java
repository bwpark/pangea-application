package com.nuri.pangea.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import com.nuri.pangea.domain.enumeration.PackStatus;

/**
 * A Pack.
 */
@Entity
@Table(name = "pack")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Pack implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 1024)
    @Column(name = "description", length = 1024)
    private String description;

    @NotNull
    @Column(name = "coin", nullable = false)
    private Integer coin;

    @NotNull
    @Column(name = "point", nullable = false)
    private Integer point;

    @Size(max = 1024)
    @Column(name = "ship_to", length = 1024)
    private String shipTo;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private PackStatus status;

    @NotNull
    @Column(name = "created", nullable = false)
    private Instant created;

    @NotNull
    @Column(name = "modified", nullable = false)
    private Instant modified;

    @OneToMany(mappedBy = "pack")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Deal> deals = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "buys", allowSetters = true)
    private Avatar me;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public Pack description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCoin() {
        return coin;
    }

    public Pack coin(Integer coin) {
        this.coin = coin;
        return this;
    }

    public void setCoin(Integer coin) {
        this.coin = coin;
    }

    public Integer getPoint() {
        return point;
    }

    public Pack point(Integer point) {
        this.point = point;
        return this;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public String getShipTo() {
        return shipTo;
    }

    public Pack shipTo(String shipTo) {
        this.shipTo = shipTo;
        return this;
    }

    public void setShipTo(String shipTo) {
        this.shipTo = shipTo;
    }

    public PackStatus getStatus() {
        return status;
    }

    public Pack status(PackStatus status) {
        this.status = status;
        return this;
    }

    public void setStatus(PackStatus status) {
        this.status = status;
    }

    public Instant getCreated() {
        return created;
    }

    public Pack created(Instant created) {
        this.created = created;
        return this;
    }

    public void setCreated(Instant created) {
        this.created = created;
    }

    public Instant getModified() {
        return modified;
    }

    public Pack modified(Instant modified) {
        this.modified = modified;
        return this;
    }

    public void setModified(Instant modified) {
        this.modified = modified;
    }

    public Set<Deal> getDeals() {
        return deals;
    }

    public Pack deals(Set<Deal> deals) {
        this.deals = deals;
        return this;
    }

    public Pack addDeal(Deal deal) {
        this.deals.add(deal);
        deal.setPack(this);
        return this;
    }

    public Pack removeDeal(Deal deal) {
        this.deals.remove(deal);
        deal.setPack(null);
        return this;
    }

    public void setDeals(Set<Deal> deals) {
        this.deals = deals;
    }

    public Avatar getMe() {
        return me;
    }

    public Pack me(Avatar avatar) {
        this.me = avatar;
        return this;
    }

    public void setMe(Avatar avatar) {
        this.me = avatar;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Pack)) {
            return false;
        }
        return id != null && id.equals(((Pack) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Pack{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
            ", coin=" + getCoin() +
            ", point=" + getPoint() +
            ", shipTo='" + getShipTo() + "'" +
            ", status='" + getStatus() + "'" +
            ", created='" + getCreated() + "'" +
            ", modified='" + getModified() + "'" +
            "}";
    }
}
