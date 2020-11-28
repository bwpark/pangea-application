package com.nuri.pangea.web.rest;

import com.nuri.pangea.service.TOSService;
import com.nuri.pangea.web.rest.errors.BadRequestAlertException;
import com.nuri.pangea.service.dto.TOSDTO;

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
 * REST controller for managing {@link com.nuri.pangea.domain.TOS}.
 */
@RestController
@RequestMapping("/api")
public class TOSResource {

    private final Logger log = LoggerFactory.getLogger(TOSResource.class);

    private static final String ENTITY_NAME = "tOS";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TOSService tOSService;

    public TOSResource(TOSService tOSService) {
        this.tOSService = tOSService;
    }

    /**
     * {@code POST  /tos} : Create a new tOS.
     *
     * @param tOSDTO the tOSDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tOSDTO, or with status {@code 400 (Bad Request)} if the tOS has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tos")
    public ResponseEntity<TOSDTO> createTOS(@Valid @RequestBody TOSDTO tOSDTO) throws URISyntaxException {
        log.debug("REST request to save TOS : {}", tOSDTO);
        if (tOSDTO.getId() != null) {
            throw new BadRequestAlertException("A new tOS cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TOSDTO result = tOSService.save(tOSDTO);
        return ResponseEntity.created(new URI("/api/tos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tos} : Updates an existing tOS.
     *
     * @param tOSDTO the tOSDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tOSDTO,
     * or with status {@code 400 (Bad Request)} if the tOSDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tOSDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tos")
    public ResponseEntity<TOSDTO> updateTOS(@Valid @RequestBody TOSDTO tOSDTO) throws URISyntaxException {
        log.debug("REST request to update TOS : {}", tOSDTO);
        if (tOSDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TOSDTO result = tOSService.save(tOSDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tOSDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /tos} : get all the tOS.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tOS in body.
     */
    @GetMapping("/tos")
    public List<TOSDTO> getAllTOS() {
        log.debug("REST request to get all TOS");
        return tOSService.findAll();
    }

    /**
     * {@code GET  /tos/:id} : get the "id" tOS.
     *
     * @param id the id of the tOSDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tOSDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tos/{id}")
    public ResponseEntity<TOSDTO> getTOS(@PathVariable Long id) {
        log.debug("REST request to get TOS : {}", id);
        Optional<TOSDTO> tOSDTO = tOSService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tOSDTO);
    }

    /**
     * {@code DELETE  /tos/:id} : delete the "id" tOS.
     *
     * @param id the id of the tOSDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tos/{id}")
    public ResponseEntity<Void> deleteTOS(@PathVariable Long id) {
        log.debug("REST request to delete TOS : {}", id);
        tOSService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
