package com.nuri.pangea.web.rest;

import com.nuri.pangea.service.ReputeService;
import com.nuri.pangea.web.rest.errors.BadRequestAlertException;
import com.nuri.pangea.service.dto.ReputeDTO;

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
 * REST controller for managing {@link com.nuri.pangea.domain.Repute}.
 */
@RestController
@RequestMapping("/api")
public class ReputeResource {

    private final Logger log = LoggerFactory.getLogger(ReputeResource.class);

    private static final String ENTITY_NAME = "repute";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ReputeService reputeService;

    public ReputeResource(ReputeService reputeService) {
        this.reputeService = reputeService;
    }

    /**
     * {@code POST  /reputes} : Create a new repute.
     *
     * @param reputeDTO the reputeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new reputeDTO, or with status {@code 400 (Bad Request)} if the repute has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/reputes")
    public ResponseEntity<ReputeDTO> createRepute(@Valid @RequestBody ReputeDTO reputeDTO) throws URISyntaxException {
        log.debug("REST request to save Repute : {}", reputeDTO);
        if (reputeDTO.getId() != null) {
            throw new BadRequestAlertException("A new repute cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ReputeDTO result = reputeService.save(reputeDTO);
        return ResponseEntity.created(new URI("/api/reputes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /reputes} : Updates an existing repute.
     *
     * @param reputeDTO the reputeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated reputeDTO,
     * or with status {@code 400 (Bad Request)} if the reputeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the reputeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/reputes")
    public ResponseEntity<ReputeDTO> updateRepute(@Valid @RequestBody ReputeDTO reputeDTO) throws URISyntaxException {
        log.debug("REST request to update Repute : {}", reputeDTO);
        if (reputeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ReputeDTO result = reputeService.save(reputeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, reputeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /reputes} : get all the reputes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of reputes in body.
     */
    @GetMapping("/reputes")
    public List<ReputeDTO> getAllReputes() {
        log.debug("REST request to get all Reputes");
        return reputeService.findAll();
    }

    /**
     * {@code GET  /reputes/:id} : get the "id" repute.
     *
     * @param id the id of the reputeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the reputeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/reputes/{id}")
    public ResponseEntity<ReputeDTO> getRepute(@PathVariable Long id) {
        log.debug("REST request to get Repute : {}", id);
        Optional<ReputeDTO> reputeDTO = reputeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(reputeDTO);
    }

    /**
     * {@code DELETE  /reputes/:id} : delete the "id" repute.
     *
     * @param id the id of the reputeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/reputes/{id}")
    public ResponseEntity<Void> deleteRepute(@PathVariable Long id) {
        log.debug("REST request to delete Repute : {}", id);
        reputeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
