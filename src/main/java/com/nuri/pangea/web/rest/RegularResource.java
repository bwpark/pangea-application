package com.nuri.pangea.web.rest;

import com.nuri.pangea.service.RegularService;
import com.nuri.pangea.web.rest.errors.BadRequestAlertException;
import com.nuri.pangea.service.dto.RegularDTO;

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
 * REST controller for managing {@link com.nuri.pangea.domain.Regular}.
 */
@RestController
@RequestMapping("/api")
public class RegularResource {

    private final Logger log = LoggerFactory.getLogger(RegularResource.class);

    private static final String ENTITY_NAME = "regular";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RegularService regularService;

    public RegularResource(RegularService regularService) {
        this.regularService = regularService;
    }

    /**
     * {@code POST  /regulars} : Create a new regular.
     *
     * @param regularDTO the regularDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new regularDTO, or with status {@code 400 (Bad Request)} if the regular has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/regulars")
    public ResponseEntity<RegularDTO> createRegular(@Valid @RequestBody RegularDTO regularDTO) throws URISyntaxException {
        log.debug("REST request to save Regular : {}", regularDTO);
        if (regularDTO.getId() != null) {
            throw new BadRequestAlertException("A new regular cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RegularDTO result = regularService.save(regularDTO);
        return ResponseEntity.created(new URI("/api/regulars/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /regulars} : Updates an existing regular.
     *
     * @param regularDTO the regularDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated regularDTO,
     * or with status {@code 400 (Bad Request)} if the regularDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the regularDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/regulars")
    public ResponseEntity<RegularDTO> updateRegular(@Valid @RequestBody RegularDTO regularDTO) throws URISyntaxException {
        log.debug("REST request to update Regular : {}", regularDTO);
        if (regularDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RegularDTO result = regularService.save(regularDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, regularDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /regulars} : get all the regulars.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of regulars in body.
     */
    @GetMapping("/regulars")
    public List<RegularDTO> getAllRegulars() {
        log.debug("REST request to get all Regulars");
        return regularService.findAll();
    }

    /**
     * {@code GET  /regulars/:id} : get the "id" regular.
     *
     * @param id the id of the regularDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the regularDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/regulars/{id}")
    public ResponseEntity<RegularDTO> getRegular(@PathVariable Long id) {
        log.debug("REST request to get Regular : {}", id);
        Optional<RegularDTO> regularDTO = regularService.findOne(id);
        return ResponseUtil.wrapOrNotFound(regularDTO);
    }

    /**
     * {@code DELETE  /regulars/:id} : delete the "id" regular.
     *
     * @param id the id of the regularDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/regulars/{id}")
    public ResponseEntity<Void> deleteRegular(@PathVariable Long id) {
        log.debug("REST request to delete Regular : {}", id);
        regularService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
