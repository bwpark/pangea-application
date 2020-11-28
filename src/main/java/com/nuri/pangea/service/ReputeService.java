package com.nuri.pangea.service;

import com.nuri.pangea.domain.Repute;
import com.nuri.pangea.repository.ReputeRepository;
import com.nuri.pangea.service.dto.ReputeDTO;
import com.nuri.pangea.service.mapper.ReputeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Repute}.
 */
@Service
@Transactional
public class ReputeService {

    private final Logger log = LoggerFactory.getLogger(ReputeService.class);

    private final ReputeRepository reputeRepository;

    private final ReputeMapper reputeMapper;

    public ReputeService(ReputeRepository reputeRepository, ReputeMapper reputeMapper) {
        this.reputeRepository = reputeRepository;
        this.reputeMapper = reputeMapper;
    }

    /**
     * Save a repute.
     *
     * @param reputeDTO the entity to save.
     * @return the persisted entity.
     */
    public ReputeDTO save(ReputeDTO reputeDTO) {
        log.debug("Request to save Repute : {}", reputeDTO);
        Repute repute = reputeMapper.toEntity(reputeDTO);
        repute = reputeRepository.save(repute);
        return reputeMapper.toDto(repute);
    }

    /**
     * Get all the reputes.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<ReputeDTO> findAll() {
        log.debug("Request to get all Reputes");
        return reputeRepository.findAll().stream()
            .map(reputeMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one repute by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ReputeDTO> findOne(Long id) {
        log.debug("Request to get Repute : {}", id);
        return reputeRepository.findById(id)
            .map(reputeMapper::toDto);
    }

    /**
     * Delete the repute by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Repute : {}", id);
        reputeRepository.deleteById(id);
    }
}
