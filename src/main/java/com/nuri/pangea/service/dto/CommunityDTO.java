package com.nuri.pangea.service.dto;

import com.nuri.pangea.domain.Avatar;
import com.nuri.pangea.domain.Emotion;
import com.nuri.pangea.domain.Interact;
import com.nuri.pangea.domain.IssueOption;
import com.nuri.pangea.domain.IssueResource;
import com.nuri.pangea.domain.enumeration.IssueStatus;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Lob;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CommunityDTO implements Serializable {
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
    private IssueStatus status;

    @NotNull
    private Instant created;

    @NotNull
    private Instant modified;

    private Set<IssueOptionDTO> options = new HashSet<>();

    private Set<IssueResourceDTO> resources = new HashSet<>();

    private Set<InteractDTO> interacts = new HashSet<>();

    private AvatarDTO me;

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

    public Set<IssueOptionDTO> getOptions() {
        return options;
    }

    public void setOptions(Set<IssueOptionDTO> options) {
        this.options = options;
    }

    public Set<IssueResourceDTO> getResources() {
        return resources;
    }

    public void setResources(Set<IssueResourceDTO> resources) {
        this.resources = resources;
    }

    public Set<InteractDTO> getInteracts() {
        return interacts;
    }

    public void setInteracts(Set<InteractDTO> interacts) {
        this.interacts = interacts;
    }

    public AvatarDTO getMe() {
        return me;
    }

    public void setMe(AvatarDTO me) {
        this.me = me;
    }
}
