package com.nuri.pangea.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import javax.persistence.Lob;
import com.nuri.pangea.domain.enumeration.InteractStatus;

/**
 * A DTO for the {@link com.nuri.pangea.domain.Interact} entity.
 */
public class InteractDTO implements Serializable {
    
    private Long id;

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
    private InteractStatus status;

    @NotNull
    private Instant created;

    @NotNull
    private Instant modified;


    private Long youId;

    private Long issueId;

    private Long parentId;

    private Long meId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public InteractStatus getStatus() {
        return status;
    }

    public void setStatus(InteractStatus status) {
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

    public Long getYouId() {
        return youId;
    }

    public void setYouId(Long avatarId) {
        this.youId = avatarId;
    }

    public Long getIssueId() {
        return issueId;
    }

    public void setIssueId(Long issueId) {
        this.issueId = issueId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long interactId) {
        this.parentId = interactId;
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
        if (!(o instanceof InteractDTO)) {
            return false;
        }

        return id != null && id.equals(((InteractDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "InteractDTO{" +
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
            ", youId=" + getYouId() +
            ", issueId=" + getIssueId() +
            ", parentId=" + getParentId() +
            ", meId=" + getMeId() +
            "}";
    }
}
