package com.nuri.pangea.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;

import com.nuri.pangea.domain.enumeration.DealOptionStatus;

/**
 * A DealOption.
 */
@Entity
@Table(name = "deal_option")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DealOption implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 128)
    @Column(name = "name", length = 128, nullable = false)
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private DealOptionStatus status;

    @NotNull
    @Column(name = "created", nullable = false)
    private Instant created;

    @NotNull
    @Column(name = "modified", nullable = false)
    private Instant modified;

    @ManyToOne
    @JsonIgnoreProperties(value = "deals", allowSetters = true)
    private Deal pack;

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

    public DealOption name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DealOptionStatus getStatus() {
        return status;
    }

    public DealOption status(DealOptionStatus status) {
        this.status = status;
        return this;
    }

    public void setStatus(DealOptionStatus status) {
        this.status = status;
    }

    public Instant getCreated() {
        return created;
    }

    public DealOption created(Instant created) {
        this.created = created;
        return this;
    }

    public void setCreated(Instant created) {
        this.created = created;
    }

    public Instant getModified() {
        return modified;
    }

    public DealOption modified(Instant modified) {
        this.modified = modified;
        return this;
    }

    public void setModified(Instant modified) {
        this.modified = modified;
    }

    public Deal getPack() {
        return pack;
    }

    public DealOption pack(Deal deal) {
        this.pack = deal;
        return this;
    }

    public void setPack(Deal deal) {
        this.pack = deal;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DealOption)) {
            return false;
        }
        return id != null && id.equals(((DealOption) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DealOption{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", status='" + getStatus() + "'" +
            ", created='" + getCreated() + "'" +
            ", modified='" + getModified() + "'" +
            "}";
    }
}
