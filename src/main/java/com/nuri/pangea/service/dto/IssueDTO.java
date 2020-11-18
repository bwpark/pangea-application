package com.nuri.pangea.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import javax.persistence.Lob;
import com.nuri.pangea.domain.enumeration.IssueStatus;

/**
 * A DTO for the {@link com.nuri.pangea.domain.Issue} entity.
 */
public class IssueDTO implements Serializable {
    
    private Long id;

    @Size(max = 128)
    private String categoryName;

    @NotNull
    @Size(max = 128)
    private String name;

    @Size(max = 1024)
    private String description;

    
    @Lob
    private String text;

    @NotNull
    private Integer coin;

    @NotNull
    private Integer point;

    @NotNull
    private Integer like;

    @NotNull
    private Integer dislike;

    @NotNull
    @Size(max = 128)
    private String author;

    @NotNull
    private Integer views;

    @NotNull
    private Integer comments;

    @NotNull
    private IssueStatus status;

    @NotNull
    private Instant created;

    @NotNull
    private Instant modified;


    private Long meId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getCoin() {
        return coin;
    }

    public void setCoin(Integer coin) {
        this.coin = coin;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public Integer getLike() {
        return like;
    }

    public void setLike(Integer like) {
        this.like = like;
    }

    public Integer getDislike() {
        return dislike;
    }

    public void setDislike(Integer dislike) {
        this.dislike = dislike;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public Integer getComments() {
        return comments;
    }

    public void setComments(Integer comments) {
        this.comments = comments;
    }

    public IssueStatus getStatus() {
        return status;
    }

    public void setStatus(IssueStatus status) {
        this.status = status;
    }

    public Instant getCreated() {
        return created;
    }

    public void setCreated(Instant created) {
        this.created = created;
    }

    public Instant getModified() {
        return modified;
    }

    public void setModified(Instant modified) {
        this.modified = modified;
    }

    public Long getMeId() {
        return meId;
    }

    public void setMeId(Long avatarId) {
        this.meId = avatarId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof IssueDTO)) {
            return false;
        }

        return id != null && id.equals(((IssueDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "IssueDTO{" +
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
            ", meId=" + getMeId() +
            "}";
    }
}
