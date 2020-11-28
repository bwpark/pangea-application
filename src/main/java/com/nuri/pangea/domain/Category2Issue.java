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

import com.nuri.pangea.domain.enumeration.Category2IssueStatus;

/**
 * A Category2Issue.
 */
@Entity
@Table(name = "category_2_issue")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Category2Issue implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 1024)
    @Column(name = "icon", length = 1024)
    private String icon;

    @NotNull
    @Size(max = 128)
    @Column(name = "name", length = 128, nullable = false)
    private String name;

    @Size(max = 1024)
    @Column(name = "description", length = 1024)
    private String description;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Category2IssueStatus status;

    @NotNull
    @Column(name = "created", nullable = false)
    private Instant created;

    @NotNull
    @Column(name = "modified", nullable = false)
    private Instant modified;

    @OneToMany(mappedBy = "parent")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Category2Issue> children = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "children", allowSetters = true)
    private Category2Issue parent;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIcon() {
        return icon;
    }

    public Category2Issue icon(String icon) {
        this.icon = icon;
        return this;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public Category2Issue name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public Category2Issue description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category2IssueStatus getStatus() {
        return status;
    }

    public Category2Issue status(Category2IssueStatus status) {
        this.status = status;
        return this;
    }

    public void setStatus(Category2IssueStatus status) {
        this.status = status;
    }

    public Instant getCreated() {
        return created;
    }

    public Category2Issue created(Instant created) {
        this.created = created;
        return this;
    }

    public void setCreated(Instant created) {
        this.created = created;
    }

    public Instant getModified() {
        return modified;
    }

    public Category2Issue modified(Instant modified) {
        this.modified = modified;
        return this;
    }

    public void setModified(Instant modified) {
        this.modified = modified;
    }

    public Set<Category2Issue> getChildren() {
        return children;
    }

    public Category2Issue children(Set<Category2Issue> category2Issues) {
        this.children = category2Issues;
        return this;
    }

    public Category2Issue addChild(Category2Issue category2Issue) {
        this.children.add(category2Issue);
        category2Issue.setParent(this);
        return this;
    }

    public Category2Issue removeChild(Category2Issue category2Issue) {
        this.children.remove(category2Issue);
        category2Issue.setParent(null);
        return this;
    }

    public void setChildren(Set<Category2Issue> category2Issues) {
        this.children = category2Issues;
    }

    public Category2Issue getParent() {
        return parent;
    }

    public Category2Issue parent(Category2Issue category2Issue) {
        this.parent = category2Issue;
        return this;
    }

    public void setParent(Category2Issue category2Issue) {
        this.parent = category2Issue;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Category2Issue)) {
            return false;
        }
        return id != null && id.equals(((Category2Issue) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Category2Issue{" +
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
