package com.nuri.pangea.service.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A DTO for the {@link com.nuri.pangea.domain.Avatar} entity.
 */
public class BasicInfoDTO implements Serializable {
    private Set<Category2avatarDTO> category2avatars = new HashSet<>();

    private Set<Category2IssueDTO> category2issues = new HashSet<>();

    public Set<Category2avatarDTO> getCategory2avatars() {
        return category2avatars;
    }

    public void setCategory2avatars(Set<Category2avatarDTO> category2avatars) {
        this.category2avatars = category2avatars;
    }

    public Set<Category2IssueDTO> getCategory2issues() {
        return category2issues;
    }

    public void setCategory2issues(Set<Category2IssueDTO> category2issues) {
        this.category2issues = category2issues;
    }
}
