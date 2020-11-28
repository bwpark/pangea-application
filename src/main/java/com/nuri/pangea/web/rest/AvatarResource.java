package com.nuri.pangea.web.rest;

import com.nuri.pangea.service.AvatarService;
import com.nuri.pangea.web.rest.errors.BadRequestAlertException;
import com.nuri.pangea.service.dto.AvatarDTO;

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
 * REST controller for managing {@link com.nuri.pangea.domain.Avatar}.
 */
@RestController
@RequestMapping("/api")
public class AvatarResource {

    private final Logger log = LoggerFactory.getLogger(AvatarResource.class);

    private static final String ENTITY_NAME = "avatar";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AvatarService avatarService;

    public AvatarResource(AvatarService avatarService) {
        this.avatarService = avatarService;
    }

    /**
     * {@code POST  /avatars} : Create a new avatar.
     *
     * @param avatarDTO the avatarDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new avatarDTO, or with status {@code 400 (Bad Request)} if the avatar has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/avatars")
    public ResponseEntity<AvatarDTO> createAvatar(@Valid @RequestBody AvatarDTO avatarDTO) throws URISyntaxException {
        log.debug("REST request to save Avatar : {}", avatarDTO);
        if (avatarDTO.getId() != null) {
            throw new BadRequestAlertException("A new avatar cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AvatarDTO result = avatarService.save(avatarDTO);
        return ResponseEntity.created(new URI("/api/avatars/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /avatars} : Updates an existing avatar.
     *
     * @param avatarDTO the avatarDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated avatarDTO,
     * or with status {@code 400 (Bad Request)} if the avatarDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the avatarDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/avatars")
    public ResponseEntity<AvatarDTO> updateAvatar(@Valid @RequestBody AvatarDTO avatarDTO) throws URISyntaxException {
        log.debug("REST request to update Avatar : {}", avatarDTO);
        if (avatarDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AvatarDTO result = avatarService.save(avatarDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, avatarDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /avatars} : get all the avatars.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of avatars in body.
     */
    @GetMapping("/avatars")
    public ResponseEntity<List<AvatarDTO>> getAllAvatars(Pageable pageable) {
        log.debug("REST request to get a page of Avatars");
        Page<AvatarDTO> page = avatarService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /avatars/:id} : get the "id" avatar.
     *
     * @param id the id of the avatarDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the avatarDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/avatars/{id}")
    public ResponseEntity<AvatarDTO> getAvatar(@PathVariable Long id) {
        log.debug("REST request to get Avatar : {}", id);
        Optional<AvatarDTO> avatarDTO = avatarService.findOne(id);
        return ResponseUtil.wrapOrNotFound(avatarDTO);
    }

    /**
     * {@code DELETE  /avatars/:id} : delete the "id" avatar.
     *
     * @param id the id of the avatarDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/avatars/{id}")
    public ResponseEntity<Void> deleteAvatar(@PathVariable Long id) {
        log.debug("REST request to delete Avatar : {}", id);
        avatarService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
