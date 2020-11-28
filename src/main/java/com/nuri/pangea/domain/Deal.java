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

import com.nuri.pangea.domain.enumeration.DealStatus;

/**
 * A Deal.
 */
@Entity
@Table(name = "deal")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Deal implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 128)
    @Column(name = "name", length = 128, nullable = false)
    private String name;

    @Size(max = 1024)
    @Column(name = "description", length = 1024)
    private String description;

    @NotNull
    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @NotNull
    @Column(name = "unit_price", nullable = false)
    private Integer unitPrice;

    @NotNull
    @Column(name = "coin", nullable = false)
    private Integer coin;

    @NotNull
    @Column(name = "point", nullable = false)
    private Integer point;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private DealStatus status;

    @NotNull
    @Column(name = "created", nullable = false)
    private Instant created;

    @NotNull
    @Column(name = "modified", nullable = false)
    private Instant modified;

    @OneToMany(mappedBy = "pack")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<DealOption> deals = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "deals", allowSetters = true)
    private Issue with;

    @ManyToOne
    @JsonIgnoreProperties(value = "deals", allowSetters = true)
    private Pack pack;

    @ManyToOne
    @JsonIgnoreProperties(value = "sales", allowSetters = true)
    private Avatar to;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Deal name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public Deal description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Deal quantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getUnitPrice() {
        return unitPrice;
    }

    public Deal unitPrice(Integer unitPrice) {
        this.unitPrice = unitPrice;
        return this;
    }

    public void setUnitPrice(Integer unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getCoin() {
        return coin;
    }

    public Deal coin(Integer coin) {
        this.coin = coin;
        return this;
    }

    public void setCoin(Integer coin) {
        this.coin = coin;
    }

    public Integer getPoint() {
        return point;
    }

    public Deal point(Integer point) {
        this.point = point;
        return this;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public DealStatus getStatus() {
        return status;
    }

    public Deal status(DealStatus status) {
        this.status = status;
        return this;
    }

    public void setStatus(DealStatus status) {
        this.status = status;
    }

    public Instant getCreated() {
        return created;
    }

    public Deal created(Instant created) {
        this.created = created;
        return this;
    }

    public void setCreated(Instant created) {
        this.created = created;
    }

    public Instant getModified() {
        return modified;
    }

    public Deal modified(Instant modified) {
        this.modified = modified;
        return this;
    }

    public void setModified(Instant modified) {
        this.modified = modified;
    }

    public Set<DealOption> getDeals() {
        return deals;
    }

    public Deal deals(Set<DealOption> dealOptions) {
        this.deals = dealOptions;
        return this;
    }

    public Deal addDeal(DealOption dealOption) {
        this.deals.add(dealOption);
        dealOption.setPack(this);
        return this;
    }

    public Deal removeDeal(DealOption dealOption) {
        this.deals.remove(dealOption);
        dealOption.setPack(null);
        return this;
    }

    public void setDeals(Set<DealOption> dealOptions) {
        this.deals = dealOptions;
    }

    public Issue getWith() {
        return with;
    }

    public Deal with(Issue issue) {
        this.with = issue;
        return this;
    }

    public void setWith(Issue issue) {
        this.with = issue;
    }

    public Pack getPack() {
        return pack;
    }

    public Deal pack(Pack pack) {
        this.pack = pack;
        return this;
    }

    public void setPack(Pack pack) {
        this.pack = pack;
    }

    public Avatar getTo() {
        return to;
    }

    public Deal to(Avatar avatar) {
        this.to = avatar;
        return this;
    }

    public void setTo(Avatar avatar) {
        this.to = avatar;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Deal)) {
            return false;
        }
        return id != null && id.equals(((Deal) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Deal{" +
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
            "}";
    }
}
