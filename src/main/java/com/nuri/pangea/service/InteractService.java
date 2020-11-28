package com.nuri.pangea.service;

import com.nuri.pangea.domain.Avatar;
import com.nuri.pangea.domain.Interact;
import com.nuri.pangea.repository.AvatarRepository;
import com.nuri.pangea.repository.InteractRepository;
import com.nuri.pangea.service.dto.InteractDTO;
import com.nuri.pangea.service.mapper.InteractMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Interact}.
 */
@Service
@Transactional
public class InteractService {
    private final Logger log = LoggerFactory.getLogger(InteractService.class);

    private final InteractRepository interactRepository;
    private final AvatarRepository avatarRepository;

    private final InteractMapper interactMapper;

    public InteractService(InteractRepository interactRepository, InteractMapper interactMapper, AvatarRepository avatarRepository) {
        this.interactRepository = interactRepository;
        this.avatarRepository = avatarRepository;
        this.interactMapper = interactMapper;
    }

    /**
     * Save a interact.
     *
     * @param interactDTO the entity to save.
     * @return the persisted entity.
     */
    public InteractDTO save(InteractDTO interactDTO) {
        log.debug("Request to save Interact : {}", interactDTO);
        Interact interact = interactMapper.toEntity(interactDTO);

        Avatar avatar = avatarRepository
            .findOneByUserIsCurrentUser()
            .orElseThrow(() -> new AuthenticationCredentialsNotFoundException("AuthenticationCredentialsNotFound"));

        interact.setMe(avatar);
        interact.setAuthor(avatar.getName());

        interact = interactRepository.save(interact);
        return interactMapper.toDto(interact);
    }

    /**
     * Get all the interacts.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<InteractDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Interacts");
        return interactRepository.findAll(pageable).map(interactMapper::toDto);
    }

    /**
     * Get one interact by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<InteractDTO> findOne(Long id) {
        log.debug("Request to get Interact : {}", id);
        return interactRepository.findById(id).map(interactMapper::toDto);
    }

    /**
     * Delete the interact by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Interact : {}", id);
        interactRepository.deleteById(id);
    }
}
