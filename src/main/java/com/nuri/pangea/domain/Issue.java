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

import com.nuri.pangea.domain.enumeration.IssueStatus;

/**
 * A Issue.
 */
@Entity
@Table(name = "issue")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Issue implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 128)
    @Column(name = "category_name", length = 128)
    private String categoryName;

    @NotNull
    @Size(max = 128)
    @Column(name = "name", length = 128, nullable = false)
    private String name;

    @Size(max = 1024)
    @Column(name = "description", length = 1024)
    private String description;

    
    @Lob
    @Column(name = "text", nullable = false)
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
    @Column(name = "views", nullable = false)
    private Integer views;

    @NotNull
    @Column(name = "comments", nullable = false)
    private Integer comments;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private IssueStatus status;

    @NotNull
    @Column(name = "created", nullable = false)
    private Instant created;

    @NotNull
    @Column(name = "modified", nullable = false)
    private Instant modified;

    @OneToMany(mappedBy = "issue")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<IssueOption> options = new HashSet<>();

    @OneToMany(mappedBy = "issue")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Interact> interacts = new HashSet<>();

    @OneToMany(mappedBy = "issue")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<IssueResource> resources = new HashSet<>();

    @OneToMany(mappedBy = "issue")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Emotion> emotions = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "issues", allowSetters = true)
    private Category category;

    @ManyToOne
    @JsonIgnoreProperties(value = "issues", allowSetters = true)
    private Avatar me;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public Issue categoryName(String categoryName) {
        this.categoryName = categoryName;
        return this;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getName() {
        return name;
    }

    public Issue name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public Issue description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getText() {
        return text;
    }

    public Issue text(String text) {
        this.text = text;
        return this;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getCoin() {
        return coin;
    }

    public Issue coin(Integer coin) {
        this.coin = coin;
        return this;
    }

    public void setCoin(Integer coin) {
        this.coin = coin;
    }

    public Integer getPoint() {
        return point;
    }

    public Issue point(Integer point) {
        this.point = point;
        return this;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public Integer getLike() {
        return like;
    }

    public Issue like(Integer like) {
        this.like = like;
        return this;
    }

    public void setLike(Integer like) {
        this.like = like;
    }

    public Integer getDislike() {
        return dislike;
    }

    public Issue dislike(Integer dislike) {
        this.dislike = dislike;
        return this;
    }

    public void setDislike(Integer dislike) {
        this.dislike = dislike;
    }

    public String getAuthor() {
        return author;
    }

    public Issue author(String author) {
        this.author = author;
        return this;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getViews() {
        return views;
    }

    public Issue views(Integer views) {
        this.views = views;
        return this;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public Integer getComments() {
        return comments;
    }

    public Issue comments(Integer comments) {
        this.comments = comments;
        return this;
    }

    public void setComments(Integer comments) {
        this.comments = comments;
    }

    public IssueStatus getStatus() {
        return status;
    }

    public Issue status(IssueStatus status) {
        this.status = status;
        return this;
    }

    public void setStatus(IssueStatus status) {
        this.status = status;
    }

    public Instant getCreated() {
        return created;
    }

    public Issue created(Instant created) {
        this.created = created;
        return this;
    }

    public void setCreated(Instant created) {
        this.created = created;
    }

    public Instant getModified() {
        return modified;
    }

    public Issue modified(Instant modified) {
        this.modified = modified;
        return this;
    }

    public void setModified(Instant modified) {
        this.modified = modified;
    }

    public Set<IssueOption> getOptions() {
        return options;
    }

    public Issue options(Set<IssueOption> issueOptions) {
        this.options = issueOptions;
        return this;
    }

    public Issue addOption(IssueOption issueOption) {
        this.options.add(issueOption);
        issueOption.setIssue(this);
        return this;
    }

    public Issue removeOption(IssueOption issueOption) {
        this.options.remove(issueOption);
        issueOption.setIssue(null);
        return this;
    }

    public void setOptions(Set<IssueOption> issueOptions) {
        this.options = issueOptions;
    }

    public Set<Interact> getInteracts() {
        return interacts;
    }

    public Issue interacts(Set<Interact> interacts) {
        this.interacts = interacts;
        return this;
    }

    public Issue addInteract(Interact interact) {
        this.interacts.add(interact);
        interact.setIssue(this);
        return this;
    }

    public Issue removeInteract(Interact interact) {
        this.interacts.remove(interact);
        interact.setIssue(null);
        return this;
    }

    public void setInteracts(Set<Interact> interacts) {
        this.interacts = interacts;
    }

    public Set<IssueResource> getResources() {
        return resources;
    }

    public Issue resources(Set<IssueResource> issueResources) {
        this.resources = issueResources;
        return this;
    }

    public Issue addResource(IssueResource issueResource) {
        this.resources.add(issueResource);
        issueResource.setIssue(this);
        return this;
    }

    public Issue removeResource(IssueResource issueResource) {
        this.resources.remove(issueResource);
        issueResource.setIssue(null);
        return this;
    }

    public void setResources(Set<IssueResource> issueResources) {
        this.resources = issueResources;
    }

    public Set<Emotion> getEmotions() {
        return emotions;
    }

    public Issue emotions(Set<Emotion> emotions) {
        this.emotions = emotions;
        return this;
    }

    public Issue addEmotion(Emotion emotion) {
        this.emotions.add(emotion);
        emotion.setIssue(this);
        return this;
    }

    public Issue removeEmotion(Emotion emotion) {
        this.emotions.remove(emotion);
        emotion.setIssue(null);
        return this;
    }

    public void setEmotions(Set<Emotion> emotions) {
        this.emotions = emotions;
    }

    public Category getCategory() {
        return category;
    }

    public Issue category(Category category) {
        this.category = category;
        return this;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Avatar getMe() {
        return me;
    }

    public Issue me(Avatar avatar) {
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
        if (!(o instanceof Issue)) {
            return false;
        }
        return id != null && id.equals(((Issue) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Issue{" +
            "id=" + getId() +
            ", categoryName='" + getCategoryName() + "'" +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", text='" + getText() + "'" +
            ", coin=" + getCoin() +
            ", point=" + getPoint() +
            ", like=" + getLike() +
            ", dislike=" + getDislike() +
            ", author='" + getAuthor() + "'" +
            ", views=" + getViews() +
            ", comments=" + getComments() +
            ", status='" + getStatus() + "'" +
            ", created='" + getCreated() + "'" +
            ", modified='" + getModified() + "'" +
            "}";
    }
}
