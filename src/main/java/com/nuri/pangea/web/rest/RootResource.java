package com.nuri.pangea.web.rest;

import com.nuri.pangea.service.Category2IssueService;
import com.nuri.pangea.service.Category2avatarService;
import com.nuri.pangea.service.dto.BasicInfoDTO;
import java.util.HashSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for managing {@link com.nuri.pangea.domain.Avatar}.
 */
@RestController
@RequestMapping("/api")
public class RootResource {
    private final Logger log = LoggerFactory.getLogger(RootResource.class);

    private final Category2avatarService category2avatarService;
    private final Category2IssueService category2IssueService;

    public RootResource(Category2avatarService category2avatarService, Category2IssueService category2IssueService) {
        this.category2avatarService = category2avatarService;
        this.category2IssueService = category2IssueService;
    }

    @GetMapping("/basic-info")
    public BasicInfoDTO getBasicInfo() {
        BasicInfoDTO info = new BasicInfoDTO();

        info.setCategory2avatars(new HashSet<>(category2avatarService.findAllwithChildren()));
        info.setCategory2issues(new HashSet<>(category2IssueService.findAllwithChildren()));

        return info;
    }
}
