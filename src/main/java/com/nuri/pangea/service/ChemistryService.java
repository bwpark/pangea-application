package com.nuri.pangea.service;

import com.nuri.pangea.domain.Chemistry;
import com.nuri.pangea.repository.ChemistryRepository;
import com.nuri.pangea.service.dto.ChemistryDTO;
import com.nuri.pangea.service.mapper.ChemistryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Chemistry}.
 */
@Service
@Transactional
public class ChemistryService {

    private final Logger log = LoggerFactory.getLogger(ChemistryService.class);

    private final ChemistryRepository chemistryRepository;

    private final ChemistryMapper chemistryMapper;

    public ChemistryService(ChemistryRepository chemistryRepository, ChemistryMapper chemistryMapper) {
        this.chemistryRepository = chemistryRepository;
        this.chemistryMapper = chemistryMapper;
    }

    /**
     * Save a chemistry.
     *
     * @param chemistryDTO the entity to save.
     * @return the persisted entity.
     */
    public ChemistryDTO save(ChemistryDTO chemistryDTO) {
        log.debug("Request to save Chemistry : {}", chemistryDTO);
        Chemistry chemistry = chemistryMapper.toEntity(chemistryDTO);
        chemistry = chemistryRepository.save(chemistry);
        return chemistryMapper.toDto(chemistry);
    }

    /**
     * Get all the chemistries.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<ChemistryDTO> findAll() {
        log.debug("Request to get all Chemistries");
        return chemistryRepository.findAll().stream()
            .map(chemistryMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one chemistry by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ChemistryDTO> findOne(Long id) {
        log.debug("Request to get Chemistry : {}", id);
        return chemistryRepository.findById(id)
            .map(chemistryMapper::toDto);
    }

    /**
     * Delete the chemistry by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Chemistry : {}", id);
        chemistryRepository.deleteById(id);
    }
}
