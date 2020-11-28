package com.nuri.pangea.service;

import com.nuri.pangea.domain.IssueResource;
import com.nuri.pangea.repository.IssueResourceRepository;
import com.nuri.pangea.service.dto.IssueResourceDTO;
import com.nuri.pangea.service.mapper.IssueResourceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link IssueResource}.
 */
@Service
@Transactional
public class IssueResourceService {

    private final Logger log = LoggerFactory.getLogger(IssueResourceService.class);

    private final IssueResourceRepository issueResourceRepository;

    private final IssueResourceMapper issueResourceMapper;

    public IssueResourceService(IssueResourceRepository issueResourceRepository, IssueResourceMapper issueResourceMapper) {
        this.issueResourceRepository = issueResourceRepository;
        this.issueResourceMapper = issueResourceMapper;
    }

    /**
     * Save a issueResource.
     *
     * @param issueResourceDTO the entity to save.
     * @return the persisted entity.
     */
    public IssueResourceDTO save(IssueResourceDTO issueResourceDTO) {
        log.debug("Request to save IssueResource : {}", issueResourceDTO);
        IssueResource issueResource = issueResourceMapper.toEntity(issueResourceDTO);
        issueResource = issueResourceRepository.save(issueResource);
        return issueResourceMapper.toDto(issueResource);
    }

    /**
     * Get all the issueResources.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<IssueResourceDTO> findAll() {
        log.debug("Request to get all IssueResources");
        return issueResourceRepository.findAll().stream()
            .map(issueResourceMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one issueResource by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<IssueResourceDTO> findOne(Long id) {
        log.debug("Request to get IssueResource : {}", id);
        return issueResourceRepository.findById(id)
            .map(issueResourceMapper::toDto);
    }

    /**
     * Delete the issueResource by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete IssueResource : {}", id);
        issueResourceRepository.deleteById(id);
    }
}
