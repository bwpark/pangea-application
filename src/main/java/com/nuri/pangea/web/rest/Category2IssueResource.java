package com.nuri.pangea.web.rest;

import com.nuri.pangea.service.Category2IssueService;
import com.nuri.pangea.web.rest.errors.BadRequestAlertException;
import com.nuri.pangea.service.dto.Category2IssueDTO;

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
 * REST controller for managing {@link com.nuri.pangea.domain.Category2Issue}.
 */
@RestController
@RequestMapping("/api")
public class Category2IssueResource {

    private final Logger log = LoggerFactory.getLogger(Category2IssueResource.class);

    private static final String ENTITY_NAME = "category2Issue";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final Category2IssueService category2IssueService;

    public Category2IssueResource(Category2IssueService category2IssueService) {
        this.category2IssueService = category2IssueService;
    }

    /**
     * {@code POST  /category-2-issues} : Create a new category2Issue.
     *
     * @param category2IssueDTO the category2IssueDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new category2IssueDTO, or with status {@code 400 (Bad Request)} if the category2Issue has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/category-2-issues")
    public ResponseEntity<Category2IssueDTO> createCategory2Issue(@Valid @RequestBody Category2IssueDTO category2IssueDTO) throws URISyntaxException {
        log.debug("REST request to save Category2Issue : {}", category2IssueDTO);
        if (category2IssueDTO.getId() != null) {
            throw new BadRequestAlertException("A new category2Issue cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Category2IssueDTO result = category2IssueService.save(category2IssueDTO);
        return ResponseEntity.created(new URI("/api/category-2-issues/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /category-2-issues} : Updates an existing category2Issue.
     *
     * @param category2IssueDTO the category2IssueDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated category2IssueDTO,
     * or with status {@code 400 (Bad Request)} if the category2IssueDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the category2IssueDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/category-2-issues")
    public ResponseEntity<Category2IssueDTO> updateCategory2Issue(@Valid @RequestBody Category2IssueDTO category2IssueDTO) throws URISyntaxException {
        log.debug("REST request to update Category2Issue : {}", category2IssueDTO);
        if (category2IssueDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Category2IssueDTO result = category2IssueService.save(category2IssueDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, category2IssueDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /category-2-issues} : get all the category2Issues.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of category2Issues in body.
     */
    @GetMapping("/category-2-issues")
    public List<Category2IssueDTO> getAllCategory2Issues() {
        log.debug("REST request to get all Category2Issues");
        return category2IssueService.findAll();
    }

    /**
     * {@code GET  /category-2-issues/:id} : get the "id" category2Issue.
     *
     * @param id the id of the category2IssueDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the category2IssueDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/category-2-issues/{id}")
    public ResponseEntity<Category2IssueDTO> getCategory2Issue(@PathVariable Long id) {
        log.debug("REST request to get Category2Issue : {}", id);
        Optional<Category2IssueDTO> category2IssueDTO = category2IssueService.findOne(id);
        return ResponseUtil.wrapOrNotFound(category2IssueDTO);
    }

    /**
     * {@code DELETE  /category-2-issues/:id} : delete the "id" category2Issue.
     *
     * @param id the id of the category2IssueDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/category-2-issues/{id}")
    public ResponseEntity<Void> deleteCategory2Issue(@PathVariable Long id) {
        log.debug("REST request to delete Category2Issue : {}", id);
        category2IssueService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
