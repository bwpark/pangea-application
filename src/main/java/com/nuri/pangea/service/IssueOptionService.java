package com.nuri.pangea.service;

import com.nuri.pangea.domain.IssueOption;
import com.nuri.pangea.repository.IssueOptionRepository;
import com.nuri.pangea.service.dto.IssueOptionDTO;
import com.nuri.pangea.service.mapper.IssueOptionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link IssueOption}.
 */
@Service
@Transactional
public class IssueOptionService {

    private final Logger log = LoggerFactory.getLogger(IssueOptionService.class);

    private final IssueOptionRepository issueOptionRepository;

    private final IssueOptionMapper issueOptionMapper;

    public IssueOptionService(IssueOptionRepository issueOptionRepository, IssueOptionMapper issueOptionMapper) {
        this.issueOptionRepository = issueOptionRepository;
        this.issueOptionMapper = issueOptionMapper;
    }

    /**
     * Save a issueOption.
     *
     * @param issueOptionDTO the entity to save.
     * @return the persisted entity.
     */
    public IssueOptionDTO save(IssueOptionDTO issueOptionDTO) {
        log.debug("Request to save IssueOption : {}", issueOptionDTO);
        IssueOption issueOption = issueOptionMapper.toEntity(issueOptionDTO);
        issueOption = issueOptionRepository.save(issueOption);
        return issueOptionMapper.toDto(issueOption);
    }

    /**
     * Get all the issueOptions.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<IssueOptionDTO> findAll() {
        log.debug("Request to get all IssueOptions");
        return issueOptionRepository.findAll().stream()
            .map(issueOptionMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one issueOption by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<IssueOptionDTO> findOne(Long id) {
        log.debug("Request to get IssueOption : {}", id);
        return issueOptionRepository.findById(id)
            .map(issueOptionMapper::toDto);
    }

    /**
     * Delete the issueOption by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete IssueOption : {}", id);
        issueOptionRepository.deleteById(id);
    }
}
