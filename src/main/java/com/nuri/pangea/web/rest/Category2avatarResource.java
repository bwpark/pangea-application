package com.nuri.pangea.web.rest;

import com.nuri.pangea.service.Category2avatarService;
import com.nuri.pangea.web.rest.errors.BadRequestAlertException;
import com.nuri.pangea.service.dto.Category2avatarDTO;

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
 * REST controller for managing {@link com.nuri.pangea.domain.Category2avatar}.
 */
@RestController
@RequestMapping("/api")
public class Category2avatarResource {

    private final Logger log = LoggerFactory.getLogger(Category2avatarResource.class);

    private static final String ENTITY_NAME = "category2avatar";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final Category2avatarService category2avatarService;

    public Category2avatarResource(Category2avatarService category2avatarService) {
        this.category2avatarService = category2avatarService;
    }

    /**
     * {@code POST  /category-2-avatars} : Create a new category2avatar.
     *
     * @param category2avatarDTO the category2avatarDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new category2avatarDTO, or with status {@code 400 (Bad Request)} if the category2avatar has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/category-2-avatars")
    public ResponseEntity<Category2avatarDTO> createCategory2avatar(@Valid @RequestBody Category2avatarDTO category2avatarDTO) throws URISyntaxException {
        log.debug("REST request to save Category2avatar : {}", category2avatarDTO);
        if (category2avatarDTO.getId() != null) {
            throw new BadRequestAlertException("A new category2avatar cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Category2avatarDTO result = category2avatarService.save(category2avatarDTO);
        return ResponseEntity.created(new URI("/api/category-2-avatars/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /category-2-avatars} : Updates an existing category2avatar.
     *
     * @param category2avatarDTO the category2avatarDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated category2avatarDTO,
     * or with status {@code 400 (Bad Request)} if the category2avatarDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the category2avatarDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/category-2-avatars")
    public ResponseEntity<Category2avatarDTO> updateCategory2avatar(@Valid @RequestBody Category2avatarDTO category2avatarDTO) throws URISyntaxException {
        log.debug("REST request to update Category2avatar : {}", category2avatarDTO);
        if (category2avatarDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Category2avatarDTO result = category2avatarService.save(category2avatarDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, category2avatarDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /category-2-avatars} : get all the category2avatars.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of category2avatars in body.
     */
    @GetMapping("/category-2-avatars")
    public List<Category2avatarDTO> getAllCategory2avatars() {
        log.debug("REST request to get all Category2avatars");
        return category2avatarService.findAll();
    }

    /**
     * {@code GET  /category-2-avatars/:id} : get the "id" category2avatar.
     *
     * @param id the id of the category2avatarDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the category2avatarDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/category-2-avatars/{id}")
    public ResponseEntity<Category2avatarDTO> getCategory2avatar(@PathVariable Long id) {
        log.debug("REST request to get Category2avatar : {}", id);
        Optional<Category2avatarDTO> category2avatarDTO = category2avatarService.findOne(id);
        return ResponseUtil.wrapOrNotFound(category2avatarDTO);
    }

    /**
     * {@code DELETE  /category-2-avatars/:id} : delete the "id" category2avatar.
     *
     * @param id the id of the category2avatarDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/category-2-avatars/{id}")
    public ResponseEntity<Void> deleteCategory2avatar(@PathVariable Long id) {
        log.debug("REST request to delete Category2avatar : {}", id);
        category2avatarService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
