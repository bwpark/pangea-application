package com.nuri.pangea.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;

import com.nuri.pangea.domain.enumeration.IssueOptionStatus;

/**
 * A IssueOption.
 */
@Entity
@Table(name = "issue_option")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class IssueOption implements Serializable {

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
    @Column(name = "coin", nullable = false)
    private Integer coin;

    @NotNull
    @Column(name = "point", nullable = false)
    private Integer point;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private IssueOptionStatus status;

    @NotNull
    @Column(name = "created", nullable = false)
    private Instant created;

    @NotNull
    @Column(name = "modified", nullable = false)
    private Instant modified;

    @ManyToOne
    @JsonIgnoreProperties(value = "options", allowSetters = true)
    private Issue issue;

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

    public IssueOption name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public IssueOption description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCoin() {
        return coin;
    }

    public IssueOption coin(Integer coin) {
        this.coin = coin;
        return this;
    }

    public void setCoin(Integer coin) {
        this.coin = coin;
    }

    public Integer getPoint() {
        return point;
    }

    public IssueOption point(Integer point) {
        this.point = point;
        return this;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public IssueOptionStatus getStatus() {
        return status;
    }

    public IssueOption status(IssueOptionStatus status) {
        this.status = status;
        return this;
    }

    public void setStatus(IssueOptionStatus status) {
        this.status = status;
    }

    public Instant getCreated() {
        return created;
    }

    public IssueOption created(Instant created) {
        this.created = created;
        return this;
    }

    public void setCreated(Instant created) {
        this.created = created;
    }

    public Instant getModified() {
        return modified;
    }

    public IssueOption modified(Instant modified) {
        this.modified = modified;
        return this;
    }

    public void setModified(Instant modified) {
        this.modified = modified;
    }

    public Issue getIssue() {
        return issue;
    }

    public IssueOption issue(Issue issue) {
        this.issue = issue;
        return this;
    }

    public void setIssue(Issue issue) {
        this.issue = issue;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof IssueOption)) {
            return false;
        }
        return id != null && id.equals(((IssueOption) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "IssueOption{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", coin=" + getCoin() +
            ", point=" + getPoint() +
            ", status='" + getStatus() + "'" +
            ", created='" + getCreated() + "'" +
            ", modified='" + getModified() + "'" +
            "}";
    }
}
