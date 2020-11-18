package com.nuri.pangea.web.rest;

import com.nuri.pangea.service.IssueResourceService;
import com.nuri.pangea.web.rest.errors.BadRequestAlertException;
import com.nuri.pangea.service.dto.IssueResourceDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.nuri.pangea.domain.IssueResource}.
 */
@RestController
@RequestMapping("/api")
public class IssueResourceResource {

    private final Logger log = LoggerFactory.getLogger(IssueResourceResource.class);

    private static final String ENTITY_NAME = "issueResource";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final IssueResourceService issueResourceService;

    public IssueResourceResource(IssueResourceService issueResourceService) {
        this.issueResourceService = issueResourceService;
    }

    /**
     * {@code POST  /issue-resources} : Create a new issueResource.
     *
     * @param issueResourceDTO the issueResourceDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new issueResourceDTO, or with status {@code 400 (Bad Request)} if the issueResource has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/issue-resources")
    public ResponseEntity<IssueResourceDTO> createIssueResource(@Valid @RequestBody IssueResourceDTO issueResourceDTO) throws URISyntaxException {
        log.debug("REST request to save IssueResource : {}", issueResourceDTO);
        if (issueResourceDTO.getId() != null) {
            throw new BadRequestAlertException("A new issueResource cannot already have an ID", ENTITY_NAME, "idexists");
        }
        IssueResourceDTO result = issueResourceService.save(issueResourceDTO);
        return ResponseEntity.created(new URI("/api/issue-resources/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /issue-resources} : Updates an existing issueResource.
     *
     * @param issueResourceDTO the issueResourceDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated issueResourceDTO,
     * or with status {@code 400 (Bad Request)} if the issueResourceDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the issueResourceDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/issue-resources")
    public ResponseEntity<IssueResourceDTO> updateIssueResource(@Valid @RequestBody IssueResourceDTO issueResourceDTO) throws URISyntaxException {
        log.debug("REST request to update IssueResource : {}", issueResourceDTO);
        if (issueResourceDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        IssueResourceDTO result = issueResourceService.save(issueResourceDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, issueResourceDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /issue-resources} : get all the issueResources.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of issueResources in body.
     */
    @GetMapping("/issue-resources")
    public List<IssueResourceDTO> getAllIssueResources() {
        log.debug("REST request to get all IssueResources");
        return issueResourceService.findAll();
    }

    /**
     * {@code GET  /issue-resources/:id} : get the "id" issueResource.
     *
     * @param id the id of the issueResourceDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the issueResourceDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/issue-resources/{id}")
    public ResponseEntity<IssueResourceDTO> getIssueResource(@PathVariable Long id) {
        log.debug("REST request to get IssueResource : {}", id);
        Optional<IssueResourceDTO> issueResourceDTO = issueResourceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(issueResourceDTO);
    }

    /**
     * {@code DELETE  /issue-resources/:id} : delete the "id" issueResource.
     *
     * @param id the id of the issueResourceDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/issue-resources/{id}")
    public ResponseEntity<Void> deleteIssueResource(@PathVariable Long id) {
        log.debug("REST request to delete IssueResource : {}", id);
        issueResourceService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
