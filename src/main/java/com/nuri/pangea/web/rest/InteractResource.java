package com.nuri.pangea.web.rest;

import com.nuri.pangea.service.InteractService;
import com.nuri.pangea.web.rest.errors.BadRequestAlertException;
import com.nuri.pangea.service.dto.InteractDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.nuri.pangea.domain.Interact}.
 */
@RestController
@RequestMapping("/api")
public class InteractResource {

    private final Logger log = LoggerFactory.getLogger(InteractResource.class);

    private static final String ENTITY_NAME = "interact";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InteractService interactService;

    public InteractResource(InteractService interactService) {
        this.interactService = interactService;
    }

    /**
     * {@code POST  /interacts} : Create a new interact.
     *
     * @param interactDTO the interactDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new interactDTO, or with status {@code 400 (Bad Request)} if the interact has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/interacts")
    public ResponseEntity<InteractDTO> createInteract(@Valid @RequestBody InteractDTO interactDTO) throws URISyntaxException {
        log.debug("REST request to save Interact : {}", interactDTO);
        if (interactDTO.getId() != null) {
            throw new BadRequestAlertException("A new interact cannot already have an ID", ENTITY_NAME, "idexists");
        }
        InteractDTO result = interactService.save(interactDTO);
        return ResponseEntity.created(new URI("/api/interacts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /interacts} : Updates an existing interact.
     *
     * @param interactDTO the interactDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated interactDTO,
     * or with status {@code 400 (Bad Request)} if the interactDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the interactDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/interacts")
    public ResponseEntity<InteractDTO> updateInteract(@Valid @RequestBody InteractDTO interactDTO) throws URISyntaxException {
        log.debug("REST request to update Interact : {}", interactDTO);
        if (interactDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        InteractDTO result = interactService.save(interactDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, interactDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /interacts} : get all the interacts.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of interacts in body.
     */
    @GetMapping("/interacts")
    public ResponseEntity<List<InteractDTO>> getAllInteracts(Pageable pageable) {
        log.debug("REST request to get a page of Interacts");
        Page<InteractDTO> page = interactService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /interacts/:id} : get the "id" interact.
     *
     * @param id the id of the interactDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the interactDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/interacts/{id}")
    public ResponseEntity<InteractDTO> getInteract(@PathVariable Long id) {
        log.debug("REST request to get Interact : {}", id);
        Optional<InteractDTO> interactDTO = interactService.findOne(id);
        return ResponseUtil.wrapOrNotFound(interactDTO);
    }

    /**
     * {@code DELETE  /interacts/:id} : delete the "id" interact.
     *
     * @param id the id of the interactDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/interacts/{id}")
    public ResponseEntity<Void> deleteInteract(@PathVariable Long id) {
        log.debug("REST request to delete Interact : {}", id);
        interactService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
