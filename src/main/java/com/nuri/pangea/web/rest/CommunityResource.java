package com.nuri.pangea.web.rest;

import com.nuri.pangea.service.CommunityService;
import com.nuri.pangea.service.dto.CommunityDTO;
import com.nuri.pangea.service.dto.CommunityLiteDTO;
import com.nuri.pangea.service.dto.InteractDTO;
import com.nuri.pangea.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * REST controller for managing {@link com.nuri.pangea.domain.Commuinity}.
 */
@RestController
@RequestMapping("/api")
public class CommunityResource {
    private final Logger log = LoggerFactory.getLogger(CommunityResource.class);

    private static final String ENTITY_NAME = "Commuinity";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CommunityService communityService;

    public CommunityResource(CommunityService communityService) {
        this.communityService = communityService;
    }

    /**
     * {@code POST  /communities} : Create a new Commuinity.
     *
     * @param CommuinityDTO the CommuinityDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new CommuinityDTO, or with status {@code 400 (Bad Request)} if the Commuinity has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/communities")
    public ResponseEntity<CommunityDTO> createCommuinity(@Valid @RequestBody CommunityDTO communityDTO) throws URISyntaxException {
        log.debug("REST request to save Commuinity : {}", communityDTO);
        if (communityDTO.getId() != null) {
            throw new BadRequestAlertException("A new Commuinity cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CommunityDTO result = communityService.save(communityDTO);
        return ResponseEntity
            .created(new URI("/api/communities/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /communities} : Updates an existing Commuinity.
     *
     * @param CommuinityDTO the CommuinityDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated CommuinityDTO,
     * or with status {@code 400 (Bad Request)} if the CommuinityDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the CommuinityDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/communities")
    public ResponseEntity<CommunityDTO> updateCommuinity(@Valid @RequestBody CommunityDTO commuinityDTO) throws URISyntaxException {
        log.debug("REST request to update Commuinity : {}", commuinityDTO);
        if (commuinityDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CommunityDTO result = communityService.save(commuinityDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, commuinityDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /communities} : get all the communities.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of communities in body.
     */
    @GetMapping("/communities")
    public ResponseEntity<List<CommunityLiteDTO>> getAllcommunities(Pageable pageable) {
        log.debug("REST request to get a page of communities");
        Page<CommunityLiteDTO> page = communityService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /communities/:id} : get the "id" Commuinity.
     *
     * @param id the id of the CommuinityDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the CommuinityDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/communities/{id}")
    public ResponseEntity<CommunityDTO> getCommuinity(@PathVariable Long id) {
        log.debug("REST request to get Commuinity : {}", id);
        Optional<CommunityDTO> CommuinityDTO = communityService.findOne(id);
        return ResponseUtil.wrapOrNotFound(CommuinityDTO);
    }

    /**
     * {@code DELETE  /communities/:id} : delete the "id" Commuinity.
     *
     * @param id the id of the CommuinityDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/communities/{id}")
    public ResponseEntity<Void> deleteCommuinity(@PathVariable Long id) {
        log.debug("REST request to delete Commuinity : {}", id);
        communityService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    @PutMapping("/communities/{id}/like")
    public ResponseEntity<CommunityDTO> likeAction(@PathVariable Long id) {
        log.debug("REST request to likeAction in Commuinity : {}", id);
        CommunityDTO result;
        try {
            result = communityService.like(id);
        } catch (Exception e) {
            throw new BadRequestAlertException("Invalid request", ENTITY_NAME, "like action");
        }
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    @PutMapping("/communities/{id}/dislike")
    public ResponseEntity<CommunityDTO> dislikeAction(@PathVariable Long id) {
        log.debug("REST request to dislikeAction in Commuinity : {}", id);
        CommunityDTO result;
        try {
            result = communityService.dislike(id);
        } catch (Exception e) {
            throw new BadRequestAlertException("Invalid request", ENTITY_NAME, "dislike action");
        }
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    @PostMapping("/communities/{id}/interact")
    public ResponseEntity<CommunityDTO> createInteract(@PathVariable Long id, @Valid @RequestBody InteractDTO interactDTO)
        throws URISyntaxException {
        log.debug("REST request to save Interact in Commuinity  : {}", interactDTO);
        if (interactDTO.getId() != null) {
            throw new BadRequestAlertException("A new Commuinity cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CommunityDTO result = communityService.save(interactDTO);
        return ResponseEntity
            .created(new URI("/api/communities/" + id))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }
}
