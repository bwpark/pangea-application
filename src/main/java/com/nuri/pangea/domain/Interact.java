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

import com.nuri.pangea.domain.enumeration.InteractStatus;

/**
 * A Interact.
 */
@Entity
@Table(name = "interact")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Interact implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(name = "text")
    private String text;

    @NotNull
    @Column(name = "coin", nullable = false)
    private Integer coin;

    @NotNull
    @Column(name = "point", nullable = false)
    private Integer point;

    @NotNull
    @Column(name = "jhi_like", nullable = false)
    private Integer like;

    @NotNull
    @Column(name = "dislike", nullable = false)
    private Integer dislike;

    @NotNull
    @Size(max = 128)
    @Column(name = "author", length = 128, nullable = false)
    private String author;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private InteractStatus status;

    @NotNull
    @Column(name = "created", nullable = false)
    private Instant created;

    @NotNull
    @Column(name = "modified", nullable = false)
    private Instant modified;

    @OneToMany(mappedBy = "parent")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Interact> children = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "interacts", allowSetters = true)
    private Avatar you;

    @ManyToOne
    @JsonIgnoreProperties(value = "interacts", allowSetters = true)
    private Issue issue;

    @ManyToOne
    @JsonIgnoreProperties(value = "children", allowSetters = true)
    private Interact parent;

    @ManyToOne
    @JsonIgnoreProperties(value = "interacts", allowSetters = true)
    private Avatar me;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public Interact text(String text) {
        this.text = text;
        return this;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getCoin() {
        return coin;
    }

    public Interact coin(Integer coin) {
        this.coin = coin;
        return this;
    }

    public void setCoin(Integer coin) {
        this.coin = coin;
    }

    public Integer getPoint() {
        return point;
    }

    public Interact point(Integer point) {
        this.point = point;
        return this;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public Integer getLike() {
        return like;
    }

    public Interact like(Integer like) {
        this.like = like;
        return this;
    }

    public void setLike(Integer like) {
        this.like = like;
    }

    public Integer getDislike() {
        return dislike;
    }

    public Interact dislike(Integer dislike) {
        this.dislike = dislike;
        return this;
    }

    public void setDislike(Integer dislike) {
        this.dislike = dislike;
    }

    public String getAuthor() {
        return author;
    }

    public Interact author(String author) {
        this.author = author;
        return this;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public InteractStatus getStatus() {
        return status;
    }

    public Interact status(InteractStatus status) {
        this.status = status;
        return this;
    }

    public void setStatus(InteractStatus status) {
        this.status = status;
    }

    public Instant getCreated() {
        return created;
    }

    public Interact created(Instant created) {
        this.created = created;
        return this;
    }

    public void setCreated(Instant created) {
        this.created = created;
    }

    public Instant getModified() {
        return modified;
    }

    public Interact modified(Instant modified) {
        this.modified = modified;
        return this;
    }

    public void setModified(Instant modified) {
        this.modified = modified;
    }

    public Set<Interact> getChildren() {
        return children;
    }

    public Interact children(Set<Interact> interacts) {
        this.children = interacts;
        return this;
    }

    public Interact addChild(Interact interact) {
        this.children.add(interact);
        interact.setParent(this);
        return this;
    }

    public Interact removeChild(Interact interact) {
        this.children.remove(interact);
        interact.setParent(null);
        return this;
    }

    public void setChildren(Set<Interact> interacts) {
        this.children = interacts;
    }

    public Avatar getYou() {
        return you;
    }

    public Interact you(Avatar avatar) {
        this.you = avatar;
        return this;
    }

    public void setYou(Avatar avatar) {
        this.you = avatar;
    }

    public Issue getIssue() {
        return issue;
    }

    public Interact issue(Issue issue) {
        this.issue = issue;
        return this;
    }

    public void setIssue(Issue issue) {
        this.issue = issue;
    }

    public Interact getParent() {
        return parent;
    }

    public Interact parent(Interact interact) {
        this.parent = interact;
        return this;
    }

    public void setParent(Interact interact) {
        this.parent = interact;
    }

    public Avatar getMe() {
        return me;
    }

    public Interact me(Avatar avatar) {
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
        if (!(o instanceof Interact)) {
            return false;
        }
        return id != null && id.equals(((Interact) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Interact{" +
            "id=" + getId() +
            ", text='" + getText() + "'" +
            ", coin=" + getCoin() +
            ", point=" + getPoint() +
            ", like=" + getLike() +
            ", dislike=" + getDislike() +
            ", author='" + getAuthor() + "'" +
            ", status='" + getStatus() + "'" +
            ", created='" + getCreated() + "'" +
            ", modified='" + getModified() + "'" +
            "}";
    }
}
