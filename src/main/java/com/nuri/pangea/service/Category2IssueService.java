package com.nuri.pangea.service;

import com.nuri.pangea.domain.Category2Issue;
import com.nuri.pangea.repository.Category2IssueRepository;
import com.nuri.pangea.service.dto.Category2IssueDTO;
import com.nuri.pangea.service.mapper.Category2IssueMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Category2Issue}.
 */
@Service
@Transactional
public class Category2IssueService {

    private final Logger log = LoggerFactory.getLogger(Category2IssueService.class);

    private final Category2IssueRepository category2IssueRepository;

    private final Category2IssueMapper category2IssueMapper;

    public Category2IssueService(Category2IssueRepository category2IssueRepository, Category2IssueMapper category2IssueMapper) {
        this.category2IssueRepository = category2IssueRepository;
        this.category2IssueMapper = category2IssueMapper;
    }

    /**
     * Save a category2Issue.
     *
     * @param category2IssueDTO the entity to save.
     * @return the persisted entity.
     */
    public Category2IssueDTO save(Category2IssueDTO category2IssueDTO) {
        log.debug("Request to save Category2Issue : {}", category2IssueDTO);
        Category2Issue category2Issue = category2IssueMapper.toEntity(category2IssueDTO);
        category2Issue = category2IssueRepository.save(category2Issue);
        return category2IssueMapper.toDto(category2Issue);
    }

    /**
     * Get all the category2Issues.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<Category2IssueDTO> findAll() {
        log.debug("Request to get all Category2Issues");
        return category2IssueRepository.findAll().stream()
            .map(category2IssueMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one category2Issue by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Category2IssueDTO> findOne(Long id) {
        log.debug("Request to get Category2Issue : {}", id);
        return category2IssueRepository.findById(id)
            .map(category2IssueMapper::toDto);
    }

    /**
     * Delete the category2Issue by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Category2Issue : {}", id);
        category2IssueRepository.deleteById(id);
    }
}
