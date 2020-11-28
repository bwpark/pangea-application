package com.nuri.pangea.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.nuri.pangea.domain.enumeration.Category2avatarStatus;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Category2avatar.
 */
@Entity
@Table(name = "category_2_avatar")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Category2avatar implements Serializable {
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
    private Category2avatarStatus status;

    @NotNull
    @Column(name = "created", nullable = false)
    private Instant created;

    @NotNull
    @Column(name = "modified", nullable = false)
    private Instant modified;

    @OneToMany(mappedBy = "parent")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Category2avatar> children = new HashSet<>();

    @OneToMany(mappedBy = "category")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Avatar> avatars = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "children", allowSetters = true)
    private Category2avatar parent;

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

    public Category2avatar icon(String icon) {
        this.icon = icon;
        return this;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public Category2avatar name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public Category2avatar description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category2avatarStatus getStatus() {
        return status;
    }

    public Category2avatar status(Category2avatarStatus status) {
        this.status = status;
        return this;
    }

    public void setStatus(Category2avatarStatus status) {
        this.status = status;
    }

    public Instant getCreated() {
        return created;
    }

    public Category2avatar created(Instant created) {
        this.created = created;
        return this;
    }

    public void setCreated(Instant created) {
        this.created = created;
    }

    public Instant getModified() {
        return modified;
    }

    public Category2avatar modified(Instant modified) {
        this.modified = modified;
        return this;
    }

    public void setModified(Instant modified) {
        this.modified = modified;
    }

    public Set<Category2avatar> getChildren() {
        return children;
    }

    public Category2avatar children(Set<Category2avatar> category2avatars) {
        this.children = category2avatars;
        return this;
    }

    public Category2avatar addChild(Category2avatar category2avatar) {
        this.children.add(category2avatar);
        category2avatar.setParent(this);
        return this;
    }

    public Category2avatar removeChild(Category2avatar category2avatar) {
        this.children.remove(category2avatar);
        category2avatar.setParent(null);
        return this;
    }

    public void setChildren(Set<Category2avatar> category2avatars) {
        this.children = category2avatars;
    }

    public Set<Avatar> getAvatars() {
        return avatars;
    }

    public Category2avatar avatars(Set<Avatar> avatars) {
        this.avatars = avatars;
        return this;
    }

    public Category2avatar addAvatar(Avatar avatar) {
        this.avatars.add(avatar);
        avatar.setCategory(this);
        return this;
    }

    public Category2avatar removeAvatar(Avatar avatar) {
        this.avatars.remove(avatar);
        avatar.setCategory(null);
        return this;
    }

    public void setAvatars(Set<Avatar> avatars) {
        this.avatars = avatars;
    }

    public Category2avatar getParent() {
        return parent;
    }

    public Category2avatar parent(Category2avatar category2avatar) {
        this.parent = category2avatar;
        return this;
    }

    public void setParent(Category2avatar category2avatar) {
        this.parent = category2avatar;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Category2avatar)) {
            return false;
        }
        return id != null && id.equals(((Category2avatar) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Category2avatar{" +
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
