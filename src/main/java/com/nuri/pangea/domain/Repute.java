package com.nuri.pangea.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;

import com.nuri.pangea.domain.enumeration.ReputeStatus;

/**
 * A Repute.
 */
@Entity
@Table(name = "repute")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Repute implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 1024)
    @Column(name = "description", length = 1024)
    private String description;

    @NotNull
    @Column(name = "grade", nullable = false)
    private Integer grade;

    @NotNull
    @Column(name = "credit", nullable = false)
    private Integer credit;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ReputeStatus status;

    @NotNull
    @Column(name = "created", nullable = false)
    private Instant created;

    @NotNull
    @Column(name = "modified", nullable = false)
    private Instant modified;

    @ManyToOne
    @JsonIgnoreProperties(value = "reputes", allowSetters = true)
    private Avatar you;

    @ManyToOne
    @JsonIgnoreProperties(value = "reputes", allowSetters = true)
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

    public Repute description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getGrade() {
        return grade;
    }

    public Repute grade(Integer grade) {
        this.grade = grade;
        return this;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Integer getCredit() {
        return credit;
    }

    public Repute credit(Integer credit) {
        this.credit = credit;
        return this;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public ReputeStatus getStatus() {
        return status;
    }

    public Repute status(ReputeStatus status) {
        this.status = status;
        return this;
    }

    public void setStatus(ReputeStatus status) {
        this.status = status;
    }

    public Instant getCreated() {
        return created;
    }

    public Repute created(Instant created) {
        this.created = created;
        return this;
    }

    public void setCreated(Instant created) {
        this.created = created;
    }

    public Instant getModified() {
        return modified;
    }

    public Repute modified(Instant modified) {
        this.modified = modified;
        return this;
    }

    public void setModified(Instant modified) {
        this.modified = modified;
    }

    public Avatar getYou() {
        return you;
    }

    public Repute you(Avatar avatar) {
        this.you = avatar;
        return this;
    }

    public void setYou(Avatar avatar) {
        this.you = avatar;
    }

    public Avatar getMe() {
        return me;
    }

    public Repute me(Avatar avatar) {
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
        if (!(o instanceof Repute)) {
            return false;
        }
        return id != null && id.equals(((Repute) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Repute{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
            ", grade=" + getGrade() +
            ", credit=" + getCredit() +
            ", status='" + getStatus() + "'" +
            ", created='" + getCreated() + "'" +
            ", modified='" + getModified() + "'" +
            "}";
    }
}
