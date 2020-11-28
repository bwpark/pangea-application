package com.nuri.pangea.web.rest;

import com.nuri.pangea.service.DealOptionService;
import com.nuri.pangea.web.rest.errors.BadRequestAlertException;
import com.nuri.pangea.service.dto.DealOptionDTO;

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
 * REST controller for managing {@link com.nuri.pangea.domain.DealOption}.
 */
@RestController
@RequestMapping("/api")
public class DealOptionResource {

    private final Logger log = LoggerFactory.getLogger(DealOptionResource.class);

    private static final String ENTITY_NAME = "dealOption";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DealOptionService dealOptionService;

    public DealOptionResource(DealOptionService dealOptionService) {
        this.dealOptionService = dealOptionService;
    }

    /**
     * {@code POST  /deal-options} : Create a new dealOption.
     *
     * @param dealOptionDTO the dealOptionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dealOptionDTO, or with status {@code 400 (Bad Request)} if the dealOption has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/deal-options")
    public ResponseEntity<DealOptionDTO> createDealOption(@Valid @RequestBody DealOptionDTO dealOptionDTO) throws URISyntaxException {
        log.debug("REST request to save DealOption : {}", dealOptionDTO);
        if (dealOptionDTO.getId() != null) {
            throw new BadRequestAlertException("A new dealOption cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DealOptionDTO result = dealOptionService.save(dealOptionDTO);
        return ResponseEntity.created(new URI("/api/deal-options/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /deal-options} : Updates an existing dealOption.
     *
     * @param dealOptionDTO the dealOptionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dealOptionDTO,
     * or with status {@code 400 (Bad Request)} if the dealOptionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dealOptionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/deal-options")
    public ResponseEntity<DealOptionDTO> updateDealOption(@Valid @RequestBody DealOptionDTO dealOptionDTO) throws URISyntaxException {
        log.debug("REST request to update DealOption : {}", dealOptionDTO);
        if (dealOptionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DealOptionDTO result = dealOptionService.save(dealOptionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dealOptionDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /deal-options} : get all the dealOptions.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dealOptions in body.
     */
    @GetMapping("/deal-options")
    public List<DealOptionDTO> getAllDealOptions() {
        log.debug("REST request to get all DealOptions");
        return dealOptionService.findAll();
    }

    /**
     * {@code GET  /deal-options/:id} : get the "id" dealOption.
     *
     * @param id the id of the dealOptionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dealOptionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/deal-options/{id}")
    public ResponseEntity<DealOptionDTO> getDealOption(@PathVariable Long id) {
        log.debug("REST request to get DealOption : {}", id);
        Optional<DealOptionDTO> dealOptionDTO = dealOptionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(dealOptionDTO);
    }

    /**
     * {@code DELETE  /deal-options/:id} : delete the "id" dealOption.
     *
     * @param id the id of the dealOptionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/deal-options/{id}")
    public ResponseEntity<Void> deleteDealOption(@PathVariable Long id) {
        log.debug("REST request to delete DealOption : {}", id);
        dealOptionService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
