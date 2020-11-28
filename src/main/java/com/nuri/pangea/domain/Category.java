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

import com.nuri.pangea.domain.enumeration.CategoryStatus;

/**
 * A Category.
 */
@Entity
@Table(name = "category")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 1024)
    @Column(name = "icon", length = 1024)
    private String icon;

    @NotNull
    @Size(max = 128)
    @Column(name = "path", length = 128, nullable = false)
    private String path;

    @NotNull
    @Size(max = 128)
    @Column(name = "name", length = 128, nullable = false)
    private String name;

    @Size(max = 1024)
    @Column(name = "description", length = 1024)
    private String description;

    @Column(name = "hit_and_sort")
    private Integer hitAndSort;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private CategoryStatus status;

    @NotNull
    @Column(name = "created", nullable = false)
    private Instant created;

    @NotNull
    @Column(name = "modified", nullable = false)
    private Instant modified;

    @OneToMany(mappedBy = "parent")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Category> children = new HashSet<>();

    @OneToMany(mappedBy = "category")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Avatar> avatars = new HashSet<>();

    @OneToMany(mappedBy = "category")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Issue> issues = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "children", allowSetters = true)
    private Category parent;

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

    public Category icon(String icon) {
        this.icon = icon;
        return this;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getPath() {
        return path;
    }

    public Category path(String path) {
        this.path = path;
        return this;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public Category name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public Category description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getHitAndSort() {
        return hitAndSort;
    }

    public Category hitAndSort(Integer hitAndSort) {
        this.hitAndSort = hitAndSort;
        return this;
    }

    public void setHitAndSort(Integer hitAndSort) {
        this.hitAndSort = hitAndSort;
    }

    public CategoryStatus getStatus() {
        return status;
    }

    public Category status(CategoryStatus status) {
        this.status = status;
        return this;
    }

    public void setStatus(CategoryStatus status) {
        this.status = status;
    }

    public Instant getCreated() {
        return created;
    }

    public Category created(Instant created) {
        this.created = created;
        return this;
    }

    public void setCreated(Instant created) {
        this.created = created;
    }

    public Instant getModified() {
        return modified;
    }

    public Category modified(Instant modified) {
        this.modified = modified;
        return this;
    }

    public void setModified(Instant modified) {
        this.modified = modified;
    }

    public Set<Category> getChildren() {
        return children;
    }

    public Category children(Set<Category> categories) {
        this.children = categories;
        return this;
    }

    public Category addChildren(Category category) {
        this.children.add(category);
        category.setParent(this);
        return this;
    }

    public Category removeChildren(Category category) {
        this.children.remove(category);
        category.setParent(null);
        return this;
    }

    public void setChildren(Set<Category> categories) {
        this.children = categories;
    }

    public Set<Avatar> getAvatars() {
        return avatars;
    }

    public Category avatars(Set<Avatar> avatars) {
        this.avatars = avatars;
        return this;
    }

    public Category addAvatar(Avatar avatar) {
        this.avatars.add(avatar);
        avatar.setCategory(this);
        return this;
    }

    public Category removeAvatar(Avatar avatar) {
        this.avatars.remove(avatar);
        avatar.setCategory(null);
        return this;
    }

    public void setAvatars(Set<Avatar> avatars) {
        this.avatars = avatars;
    }

    public Set<Issue> getIssues() {
        return issues;
    }

    public Category issues(Set<Issue> issues) {
        this.issues = issues;
        return this;
    }

    public Category addIssue(Issue issue) {
        this.issues.add(issue);
        issue.setCategory(this);
        return this;
    }

    public Category removeIssue(Issue issue) {
        this.issues.remove(issue);
        issue.setCategory(null);
        return this;
    }

    public void setIssues(Set<Issue> issues) {
        this.issues = issues;
    }

    public Category getParent() {
        return parent;
    }

    public Category parent(Category category) {
        this.parent = category;
        return this;
    }

    public void setParent(Category category) {
        this.parent = category;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Category)) {
            return false;
        }
        return id != null && id.equals(((Category) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Category{" +
            "id=" + getId() +
            ", icon='" + getIcon() + "'" +
            ", path='" + getPath() + "'" +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", hitAndSort=" + getHitAndSort() +
            ", status='" + getStatus() + "'" +
            ", created='" + getCreated() + "'" +
            ", modified='" + getModified() + "'" +
            "}";
    }
}
