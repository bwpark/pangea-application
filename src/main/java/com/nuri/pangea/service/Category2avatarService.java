package com.nuri.pangea.service;

import com.nuri.pangea.domain.Category2avatar;
import com.nuri.pangea.repository.Category2avatarRepository;
import com.nuri.pangea.service.dto.Category2avatarDTO;
import com.nuri.pangea.service.mapper.Category2avatarMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Category2avatar}.
 */
@Service
@Transactional
public class Category2avatarService {

    private final Logger log = LoggerFactory.getLogger(Category2avatarService.class);

    private final Category2avatarRepository category2avatarRepository;

    private final Category2avatarMapper category2avatarMapper;

    public Category2avatarService(Category2avatarRepository category2avatarRepository, Category2avatarMapper category2avatarMapper) {
        this.category2avatarRepository = category2avatarRepository;
        this.category2avatarMapper = category2avatarMapper;
    }

    /**
     * Save a category2avatar.
     *
     * @param category2avatarDTO the entity to save.
     * @return the persisted entity.
     */
    public Category2avatarDTO save(Category2avatarDTO category2avatarDTO) {
        log.debug("Request to save Category2avatar : {}", category2avatarDTO);
        Category2avatar category2avatar = category2avatarMapper.toEntity(category2avatarDTO);
        category2avatar = category2avatarRepository.save(category2avatar);
        return category2avatarMapper.toDto(category2avatar);
    }

    /**
     * Get all the category2avatars.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<Category2avatarDTO> findAll() {
        log.debug("Request to get all Category2avatars");
        return category2avatarRepository.findAll().stream()
            .map(category2avatarMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one category2avatar by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Category2avatarDTO> findOne(Long id) {
        log.debug("Request to get Category2avatar : {}", id);
        return category2avatarRepository.findById(id)
            .map(category2avatarMapper::toDto);
    }

    /**
     * Delete the category2avatar by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Category2avatar : {}", id);
        category2avatarRepository.deleteById(id);
    }
}
